package org.army.common.accounting.bl.impl.generate;

import org.army.common.accounting.AccountingConstants;
import org.army.common.accounting.common.AccountingException;
import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.FinalAccountElement;
import org.army.common.accounting.entity.FinalAccountElementGroup;
import org.army.common.accounting.entity.FinalAccountStructure;
import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.to.common.OrganizationTO;
import org.army.base.common.to.Range;
import org.army.common.accounting.to.finalaccount.FinalAccountsItemGroupTO;
import org.army.common.accounting.to.finalaccount.FinalAccountsItemLedgerTO;
import org.army.common.accounting.util.AccountsTransformerToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class FinalAccountsGenerator {

    @Autowired
    private AccountsGenerateDao accountsGenerateDao;

    public List<FinalAccountsItemGroupTO> generateFinalAccountsElements(OrganizationTO organization, String finalAccountType, Range<Date> range) throws AccountingException {

        List<FinalAccountsItemGroupTO> finalAccountsItemGroupTOs;

        List<LedgerAccount> ledgerAccounts = accountsGenerateDao.getLedgerAccounts(organization);

        Map<Long, BigDecimal> ledgerAccountBalances = getLedgerAccountBalances(ledgerAccounts, range);

        FinalAccountStructure finalAccountStructure = accountsGenerateDao.getFinalAccountStructure(organization, finalAccountType);

        if (finalAccountStructure != null)  {
            finalAccountsItemGroupTOs = buildFinalAccountsStructure(finalAccountStructure, ledgerAccounts, ledgerAccountBalances);
        } else {
            if (AccountingConstants.FinalAccountType.BALANCE_SHEET.equals(finalAccountType)) {
                finalAccountsItemGroupTOs = buildDefaultBalanceSheetStructure(ledgerAccounts, ledgerAccountBalances);
            } else if (AccountingConstants.FinalAccountType.PROFIT_AND_LOSS_ACCOUNT.equals(finalAccountType)) {
                throw AccountingException
                        .builder()
                        .message(AccountingInternalConstants.ErrorCode.PROFIT_AND_LOSS_ACCOUNT_UNDEFINED)
                        .build();
            } else {
                throw AccountingException
                        .builder()
                        .message(AccountingInternalConstants.ErrorCode.FINAL_ACCOUNT_TYPE_UNDEFINED)
                        .build();
            }
        }


        return finalAccountsItemGroupTOs;
    }

    private Map<Long, BigDecimal> getLedgerAccountBalances(List<LedgerAccount> ledgerAccounts, Range<Date> range) {

        Map<Long, BigDecimal> ledgerAccountBalances = new HashMap<>();

        List<Long> assetsLiabilityEquityLedgers = new ArrayList<>();
        Map<Long, BigDecimal> assetsLiabilityEquityOpeningBalances = new HashMap<>();

        // opening balances: ASSET, LIABILITY, EQUITY
        ledgerAccounts
                .stream()
                .filter(ledgerAccount ->
                        AccountingConstants.LedgerCategory.ASSET.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.LIABILITY.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.EQUITY.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.NONE.equals(ledgerAccount.getLedgerCategory()))
                .forEach(ledgerAccount -> {
                    assetsLiabilityEquityLedgers.add(ledgerAccount.getLedgerAccountId());

                    BigDecimal openingBalance = BigDecimal.ZERO;
                    if (!ledgerAccount.getOpeningBalance().isEmpty()) {
                        openingBalance = ledgerAccount.getOpeningBalance().get(0).getBalance();
                    }
                    assetsLiabilityEquityOpeningBalances.put(ledgerAccount.getLedgerAccountId(), openingBalance);
                });

        List<Long> incomeExpenseLedgers = ledgerAccounts
                .stream()
                .filter(ledgerAccount ->
                        AccountingConstants.LedgerCategory.INCOME.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.EXPENSE.equals(ledgerAccount.getLedgerCategory()))
                .map(LedgerAccount::getLedgerAccountId)
                .collect(Collectors.toList());

        // final balance: ASSET, LIABILITY, EQUITY
        Map<Long, BigDecimal> assetsLiabilityEquityBalances = new HashMap<>();
        accountsGenerateDao.getLedgerTransactionsSum(assetsLiabilityEquityLedgers, range.getTo())
                .forEach((ledgerAccountId, ledgerTransactionsSum) -> {
                    BigDecimal openingBalance = assetsLiabilityEquityOpeningBalances.get(ledgerAccountId);
                    assetsLiabilityEquityBalances.put(ledgerAccountId, ledgerTransactionsSum.add(openingBalance));
                });

        // final balance: INCOME, EXPENSE
        Map<Long, BigDecimal> incomeExpenseBalances = accountsGenerateDao.getLedgerTransactionsSum(incomeExpenseLedgers, range);

        assetsLiabilityEquityBalances.forEach((accountId, balance) -> ledgerAccountBalances.put(accountId, balance.abs()));
        incomeExpenseBalances.forEach((accountId, balance) -> ledgerAccountBalances.put(accountId, balance.abs()));

        return ledgerAccountBalances;
    }

    private List<FinalAccountsItemGroupTO> buildDefaultBalanceSheetStructure(List<LedgerAccount> ledgerAccounts, Map<Long, BigDecimal> ledgerAccountBalances) {

        List<FinalAccountsItemGroupTO> finalAccountMainGroups = new ArrayList<>();
        Map<String, List<FinalAccountsItemLedgerTO>> ledgerCategories = new HashMap<>();
        FinalAccountsItemGroupTO finalAccountsItemGroupTO;
        List<FinalAccountsItemGroupTO> finalAccountSubGroups;
        FinalAccountsItemGroupTO finalAccountsItemSubGroupTO;

        List<String> ledgerAccountCategories = Arrays.asList(
                AccountingConstants.LedgerCategory.ASSET,
                AccountingConstants.LedgerCategory.LIABILITY,
                AccountingConstants.LedgerCategory.INCOME,
                AccountingConstants.LedgerCategory.EXPENSE,
                AccountingConstants.LedgerCategory.EQUITY,
                AccountingConstants.LedgerCategory.NONE);

        ledgerAccountCategories.forEach(ledgerAccountCategory -> ledgerCategories.put(ledgerAccountCategory, new ArrayList<>()));

        ledgerAccounts.forEach(ledgerAccount -> {
            BigDecimal ledgerAccountBalance = ledgerAccountBalances.getOrDefault(ledgerAccount.getLedgerAccountId(), BigDecimal.ZERO);

            FinalAccountsItemLedgerTO finalAccountsItemLedgerTO = new FinalAccountsItemLedgerTO();
            finalAccountsItemLedgerTO.setLedgerAccount(AccountsTransformerToTO.toLedgerAccountTO(ledgerAccount));
            finalAccountsItemLedgerTO.setBalance(ledgerAccountBalance);

            ledgerCategories.get(ledgerAccount.getLedgerCategory()).add(finalAccountsItemLedgerTO);
        });

        // group-1
        finalAccountsItemGroupTO = new FinalAccountsItemGroupTO();
        finalAccountSubGroups = new ArrayList<>();

        finalAccountsItemSubGroupTO = new FinalAccountsItemGroupTO();
        finalAccountsItemSubGroupTO.setGroupCode(AccountingConstants.LedgerCategory.ASSET);
        finalAccountsItemSubGroupTO.setGroupName(AccountingConstants.LedgerCategoryName.ASSET);
        finalAccountsItemSubGroupTO.setLedgers(ledgerCategories.get(AccountingConstants.LedgerCategory.ASSET));
        finalAccountsItemSubGroupTO.setOperator(AccountingConstants.FinalAccountOperator.ADD);
        finalAccountMainGroups.add(finalAccountsItemSubGroupTO);

        finalAccountsItemSubGroupTO = new FinalAccountsItemGroupTO();
        finalAccountsItemSubGroupTO.setGroupCode(AccountingConstants.LedgerCategory.LIABILITY);
        finalAccountsItemSubGroupTO.setGroupName(AccountingConstants.LedgerCategoryName.LIABILITY);
        finalAccountsItemSubGroupTO.setLedgers(ledgerCategories.get(AccountingConstants.LedgerCategory.LIABILITY));
        finalAccountsItemSubGroupTO.setOperator(AccountingConstants.FinalAccountOperator.SUBTRACT);
        finalAccountMainGroups.add(finalAccountsItemSubGroupTO);

        finalAccountsItemGroupTO.setGroups(finalAccountSubGroups);
        finalAccountMainGroups.add(finalAccountsItemGroupTO);

        // group-2
        finalAccountsItemGroupTO = new FinalAccountsItemGroupTO();
        finalAccountSubGroups = new ArrayList<>();

        finalAccountsItemSubGroupTO = new FinalAccountsItemGroupTO();
        finalAccountsItemSubGroupTO.setGroupCode(AccountingConstants.LedgerCategory.EQUITY);
        finalAccountsItemSubGroupTO.setGroupName(AccountingConstants.LedgerCategoryName.EQUITY);
        finalAccountsItemSubGroupTO.setLedgers(ledgerCategories.get(AccountingConstants.LedgerCategory.EQUITY));
        finalAccountsItemSubGroupTO.setOperator(AccountingConstants.FinalAccountOperator.ADD);
        finalAccountMainGroups.add(finalAccountsItemSubGroupTO);

        finalAccountsItemSubGroupTO = new FinalAccountsItemGroupTO();
        finalAccountsItemSubGroupTO.setGroupCode(AccountingConstants.LedgerCategory.INCOME);
        finalAccountsItemSubGroupTO.setGroupName(AccountingConstants.LedgerCategoryName.INCOME);
        finalAccountsItemSubGroupTO.setLedgers(ledgerCategories.get(AccountingConstants.LedgerCategory.INCOME));
        finalAccountsItemSubGroupTO.setOperator(AccountingConstants.FinalAccountOperator.ADD);
        finalAccountMainGroups.add(finalAccountsItemSubGroupTO);

        finalAccountsItemSubGroupTO = new FinalAccountsItemGroupTO();
        finalAccountsItemSubGroupTO.setGroupCode(AccountingConstants.LedgerCategory.EXPENSE);
        finalAccountsItemSubGroupTO.setGroupName(AccountingConstants.LedgerCategoryName.EXPENSE);
        finalAccountsItemSubGroupTO.setLedgers(ledgerCategories.get(AccountingConstants.LedgerCategory.EXPENSE));
        finalAccountsItemSubGroupTO.setOperator(AccountingConstants.FinalAccountOperator.SUBTRACT);
        finalAccountMainGroups.add(finalAccountsItemSubGroupTO);

        finalAccountsItemGroupTO.setGroups(finalAccountSubGroups);
        finalAccountMainGroups.add(finalAccountsItemGroupTO);

        // ASSET - LIABILITY = EQUITY + ( INCOME - EXPENSE )
        return finalAccountMainGroups;
    }

    private List<FinalAccountsItemGroupTO> buildFinalAccountsStructure(FinalAccountStructure finalAccountStructure, List<LedgerAccount> ledgerAccounts, Map<Long, BigDecimal> ledgerAccountBalances) {
        List<FinalAccountsItemGroupTO> finalAccountsItemGroups = new ArrayList<>();

        Map<Long, FinalAccountsItemLedgerTO> finalAccountsItemLedgers = getFinalAccountsItemLedgers(ledgerAccounts, ledgerAccountBalances);
        finalAccountStructure.getGroups()
                .forEach(group -> finalAccountsItemGroups.add(buildFinalAccountsItemGroup(group, finalAccountsItemLedgers)));

        return finalAccountsItemGroups;
    }

    private FinalAccountsItemGroupTO buildFinalAccountsItemGroup(FinalAccountElementGroup group, Map<Long, FinalAccountsItemLedgerTO> ledgerAccounts) {
        FinalAccountsItemGroupTO groupTO = new FinalAccountsItemGroupTO();

        groupTO.setGroupName(group.getGroupHeader());
        groupTO.setBalanceName(group.getGroupFooter());

        List<FinalAccountsItemGroupTO> subGroups = new ArrayList<>();
        group.getSubGroups()
                .forEach(subGroup -> subGroups.add(buildFinalAccountsItemGroup(subGroup, ledgerAccounts)));

        List<FinalAccountsItemLedgerTO> ledgers = new ArrayList<>();
        group.getElements()
                .forEach(element -> ledgers.add(buildFinalAccountsItemGroup(element, ledgerAccounts.get(element.getLedgerAccount().getLedgerAccountId()))));

        groupTO.setGroups(subGroups);
        groupTO.setLedgers(ledgers);

        AtomicReference<BigDecimal> groupBalance = new AtomicReference<>();
        groupBalance.set(BigDecimal.ZERO);

        subGroups.forEach(subGroup -> groupBalance.set(groupBalance.get().add(subGroup.getBalance())));
        ledgers.forEach(ledger -> groupBalance.set(groupBalance.get().add(ledger.getBalance())));

        BigDecimal balance = groupBalance.get();
        if (AccountingConstants.FinalAccountOperator.SUBTRACT.equals(group.getOperator())) {
            balance = balance.negate();
        }

        groupTO.setOperator(group.getOperator());
        groupTO.setBalance(balance);

        return groupTO;
    }

    private FinalAccountsItemLedgerTO buildFinalAccountsItemGroup(FinalAccountElement element, FinalAccountsItemLedgerTO ledgerTO) {

        BigDecimal balance = ledgerTO.getBalance();
        if (AccountingConstants.FinalAccountOperator.SUBTRACT.equals(element.getOperator())) {
            balance = balance.negate();
        }
        ledgerTO.setBalance(balance);

        return ledgerTO;
    }

    private Map<Long, FinalAccountsItemLedgerTO> getFinalAccountsItemLedgers(List<LedgerAccount> ledgerAccounts, Map<Long, BigDecimal> ledgerAccountBalances) {
        Map<Long, FinalAccountsItemLedgerTO> finalAccountsItemLedgers = new HashMap<>();

        ledgerAccounts.forEach(ledgerAccount -> {

            BigDecimal ledgerAccountBalance = ledgerAccountBalances.getOrDefault(ledgerAccount.getLedgerAccountId(), BigDecimal.ZERO);

            FinalAccountsItemLedgerTO finalAccountsItemLedgerTO = new FinalAccountsItemLedgerTO();
            finalAccountsItemLedgerTO.setLedgerAccount(AccountsTransformerToTO.toLedgerAccountTO(ledgerAccount));
            finalAccountsItemLedgerTO.setBalance(ledgerAccountBalance);
            finalAccountsItemLedgers.put(ledgerAccount.getLedgerAccountId(), finalAccountsItemLedgerTO);

        });

        return finalAccountsItemLedgers;
    }
}
