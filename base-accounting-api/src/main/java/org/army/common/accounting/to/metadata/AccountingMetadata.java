package org.army.common.accounting.to.metadata;

public interface AccountingMetadata {

    interface TransactionCategory {
        String CREDIT = "CREDIT";
        String DEBIT = "DEBIT";
    }

    interface LedgerCategory {
        String REVENUE = "REVENUE";
        String EXPENSE = "DEBIT";
        String ASSET = "ASSET";
        String LIABILITY = "LIABILITY";
    }

    interface FinalAccountType {
        String PROFIT_AND_LOSS_ACCOUNT = "PROFIT_AND_LOSS_ACCOUNT";
        String BALANCE_SHEET = "BALANCE_SHEET";
    }
}
