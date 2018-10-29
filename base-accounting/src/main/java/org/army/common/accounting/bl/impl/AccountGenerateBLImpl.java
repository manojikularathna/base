package org.army.common.accounting.bl.impl;

import org.army.common.accounting.AccountingConstants;
import org.army.common.accounting.bl.AccountGenerateBL;
import org.army.common.accounting.bl.impl.generate.CashBookGenerator;
import org.army.common.accounting.bl.impl.generate.FinalAccountsGenerator;
import org.army.common.accounting.bl.impl.generate.LedgerAccountsGenerator;
import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;
import org.army.common.accounting.to.cashbook.CashBookTO;
import org.army.common.accounting.to.common.GeneratePeriod;
import org.army.common.accounting.to.finalaccount.AccountsGenerateTO;
import org.army.common.accounting.to.finalaccount.FinalAccountsItemGroupTO;
import org.army.common.accounting.to.ledger.LedgerAccountTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountGenerateBLImpl implements AccountGenerateBL {

    private static Logger logger = LoggerFactory.getLogger(AccountGenerateBLImpl.class);

    @Autowired
    private AccountsGenerateDao accountsGenerateDao;

    @Autowired
    private CashBookGenerator cashBookGenerator;

    @Autowired
    private LedgerAccountsGenerator ledgerAccountsGenerator;

    @Autowired
    private FinalAccountsGenerator finalAccountsGenerator;


    public DayBookResponse generateDayBook(AccountingRequest<AccountsGenerateTO> request) {
        DayBookResponse dayBookResponse = new DayBookResponse();

        try {

            GeneratePeriod generatePeriod = request.getPayload().getPeriod();
            List<CashBook> cashBooks = accountsGenerateDao.getCashBooks(request.getOrganization());

            List<CashBookTO> cashBookTOs = new ArrayList<>();
            cashBooks.forEach(cashBook -> cashBookTOs.add(cashBookGenerator.generateCashBookTO(cashBook, generatePeriod)));

            dayBookResponse.setCashBooks(cashBookTOs);
            dayBookResponse.setSuccess(true);

        } catch (Exception e) {
            dayBookResponse.setMessage(AccountingInternalConstants.ErrorCode.ERROR);
            dayBookResponse.setSuccess(false);
            logger.error("Account Generate - DayBooks -- ", e);
        }

        return dayBookResponse;
    }

    public LedgerResponse generateLedger(AccountingRequest<AccountsGenerateTO> request) {
        LedgerResponse ledgerResponse = new LedgerResponse();

        try {
            GeneratePeriod generatePeriod = request.getPayload().getPeriod();
            List<LedgerAccount> ledgerAccounts = accountsGenerateDao.getLedgerAccounts(request.getOrganization());

            List<LedgerAccountTO> ledgerAccountTOs = new ArrayList<>();
            ledgerAccounts.forEach(ledgerAccount -> ledgerAccountTOs.add(ledgerAccountsGenerator.generateLedgerAccountTO(ledgerAccount, generatePeriod)));

            ledgerResponse.setLedgers(ledgerAccountTOs);
            ledgerResponse.setSuccess(true);

        } catch (Exception e) {
            ledgerResponse.setMessage(AccountingInternalConstants.ErrorCode.ERROR);
            ledgerResponse.setSuccess(false);
            logger.error("Account Generate - Ledgers -- ", e);
        }

        return ledgerResponse;
    }

    public FinalAccountResponse generateProfitAndLossAccount(AccountingRequest<AccountsGenerateTO> request){

        FinalAccountResponse finalAccountResponse = new FinalAccountResponse();

        try {

            GeneratePeriod generatePeriod = request.getPayload().getPeriod();
            List<FinalAccountsItemGroupTO> finalAccountsItemGroupTOs = finalAccountsGenerator
                    .generateFinalAccountsElements(request.getOrganization(), AccountingConstants.FinalAccountType.PROFIT_AND_LOSS_ACCOUNT, generatePeriod);

            finalAccountResponse.setAccountType(AccountingConstants.FinalAccountType.PROFIT_AND_LOSS_ACCOUNT);
            finalAccountResponse.setGroups(finalAccountsItemGroupTOs);
            finalAccountResponse.setSuccess(true);


        } catch (Exception e) {
            finalAccountResponse.setMessage(AccountingInternalConstants.ErrorCode.ERROR);
            finalAccountResponse.setSuccess(false);
            logger.error("Account Generate - Profit and Loss Account -- ", e);
        }

        return finalAccountResponse;
    }

    public FinalAccountResponse generateBalanceSheet(AccountingRequest<AccountsGenerateTO> request){

        FinalAccountResponse finalAccountResponse = new FinalAccountResponse();

        try {

            GeneratePeriod generatePeriod = request.getPayload().getPeriod();
            List<FinalAccountsItemGroupTO> finalAccountsItemGroupTOs = finalAccountsGenerator
                    .generateFinalAccountsElements(request.getOrganization(), AccountingConstants.FinalAccountType.BALANCE_SHEET, generatePeriod);

            finalAccountResponse.setAccountType(AccountingConstants.FinalAccountType.BALANCE_SHEET);
            finalAccountResponse.setGroups(finalAccountsItemGroupTOs);
            finalAccountResponse.setSuccess(true);

        } catch (Exception e) {
            finalAccountResponse.setMessage(AccountingInternalConstants.ErrorCode.ERROR);
            finalAccountResponse.setSuccess(false);
            logger.error("Account Generate - Balance -- ", e);
        }

        return finalAccountResponse;
    }

}
