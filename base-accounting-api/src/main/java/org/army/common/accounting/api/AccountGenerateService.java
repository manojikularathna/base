package org.army.common.accounting.api;

import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;
import org.army.common.accounting.to.finalaccount.AccountsGenerateTO;

public interface AccountGenerateService {

    DayBookResponse generateDayBook(AccountingRequest<AccountsGenerateTO> request);

    LedgerResponse generateLedger(AccountingRequest<AccountsGenerateTO> request);

    FinalAccountResponse generateProfitAndLossAccount(AccountingRequest<AccountsGenerateTO> request);

    FinalAccountResponse generateBalanceSheet(AccountingRequest<AccountsGenerateTO> request);

}
