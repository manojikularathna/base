package org.army.common.accounting.bl.impl;

import org.army.common.accounting.bl.AccountGenerateBL;
import org.army.common.accounting.to.AccountsGenerateRequest;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;
import org.springframework.stereotype.Service;

@Service
public class AccountGenerateBLImpl implements AccountGenerateBL {

    public DayBookResponse generateDayBook(AccountsGenerateRequest request) {return null;}

    public LedgerResponse generateLedger(AccountsGenerateRequest request){return null;}

    public FinalAccountResponse generateProfitAndLossAccount(AccountsGenerateRequest request){return null;}

    public FinalAccountResponse generateBalanceSheet(AccountsGenerateRequest request){return null;}

}
