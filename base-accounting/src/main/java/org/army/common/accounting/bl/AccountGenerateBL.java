package org.army.common.accounting.bl;

import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.finalaccount.AccountsGenerateTO;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;

public interface AccountGenerateBL {

    DayBookResponse generateDayBook(AccountingRequest<AccountsGenerateTO> request);

    LedgerResponse generateLedger(AccountingRequest<AccountsGenerateTO> request);

    FinalAccountResponse generateProfitAndLossAccount(AccountingRequest<AccountsGenerateTO> request);

    FinalAccountResponse generateBalanceSheet(AccountingRequest<AccountsGenerateTO> request);

}
