package org.army.common.accounting.bl.impl.generate;

import org.army.common.accounting.AccountingConstants;
import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.FinalAccountStructure;
import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.to.common.OrganizationTO;
import org.army.common.accounting.to.common.Range;
import org.army.common.accounting.to.finalaccount.FinalAccountsItemGroupTO;
import org.army.common.accounting.to.ledger.LedgerAccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinalAccountsGenerator {

    @Autowired
    private AccountsGenerateDao accountsGenerateDao;

    public List<FinalAccountsItemGroupTO> generateFinalAccountsElements(OrganizationTO organization, String finalAccountType, Range<Date> range) {

        List<FinalAccountsItemGroupTO> finalAccountsItemGroupTOs = new ArrayList<>();

        List<LedgerAccount> ledgerAccounts = accountsGenerateDao.getLedgerAccounts(organization);

        Map<Long, BigDecimal> ledgerAccountBalances = getLedgerAccountBalances(ledgerAccounts, range);

        FinalAccountStructure finalAccountStructure = accountsGenerateDao.getFinalAccountStructure(organization, finalAccountType);


        return finalAccountsItemGroupTOs;
    }

    private Map<Long, BigDecimal> getLedgerAccountBalances(List<LedgerAccount> ledgerAccounts, Range<Date> range) {

        Map<Long, BigDecimal> ledgerAccountBalances = new HashMap<>();

        List<Long> assetsLiabilityEquityLedgers = new ArrayList<>();
        Map<Long, BigDecimal> assetsLiabilityEquityOpeningBalances = new HashMap<>();

        ledgerAccounts
                .stream()
                .filter(ledgerAccount ->
                        AccountingConstants.LedgerCategory.ASSET.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.LIABILITY.equals(ledgerAccount.getLedgerCategory()) ||
                                AccountingConstants.LedgerCategory.EQUITY.equals(ledgerAccount.getLedgerCategory()))
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

        Map<Long, BigDecimal> assetsLiabilityEquityBalances = new HashMap<>();
        accountsGenerateDao.getLedgerTransactionsSum(assetsLiabilityEquityLedgers, range.getTo())
                .forEach((ledgerAccountId, ledgerTransactionsSum) -> {
                    BigDecimal openingBalance = assetsLiabilityEquityOpeningBalances.get(ledgerAccountId);
                    assetsLiabilityEquityBalances.put(ledgerAccountId, ledgerTransactionsSum.add(openingBalance));
                });

        Map<Long, BigDecimal> incomeExpenseBalances = accountsGenerateDao.getLedgerTransactionsSum(incomeExpenseLedgers, range);

        ledgerAccountBalances.putAll(assetsLiabilityEquityBalances);
        ledgerAccountBalances.putAll(incomeExpenseBalances);

        return ledgerAccountBalances;
    }



}
