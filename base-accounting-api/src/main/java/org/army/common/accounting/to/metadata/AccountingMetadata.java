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

}
