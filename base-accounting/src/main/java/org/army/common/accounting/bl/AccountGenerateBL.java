package org.army.common.accounting.bl;

import org.army.common.accounting.to.AccountsGenerateRequest;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;

public interface AccountGenerateBL {

    DayBookResponse generateDayBook(AccountsGenerateRequest request);

    LedgerResponse generateLedger(AccountsGenerateRequest request);

    FinalAccountResponse generateProfitAndLossAccount(AccountsGenerateRequest request);

    FinalAccountResponse generateBalanceSheet(AccountsGenerateRequest request);

}
