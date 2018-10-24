package org.army.common.accounting.api;

import org.army.common.accounting.to.*;

public interface AccountGenerateService {

    DayBookResponse generateDayBook(AccountsGenerateRequest request);

    LedgerResponse generateLedger(AccountsGenerateRequest request);

    FinalAccountResponse generateProfitAndLossAccount(AccountsGenerateRequest request);

    FinalAccountResponse generateBalanceSheet(AccountsGenerateRequest request);

}
