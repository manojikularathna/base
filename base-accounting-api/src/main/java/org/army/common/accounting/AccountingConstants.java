package org.army.common.accounting;

public interface AccountingConstants {

    interface TransactionCategory {
        String CREDIT = "C";
        String DEBIT = "D";
        String NONE = "N";
    }

    interface LedgerCategory {
        String INCOME = "I";
        String EXPENSE = "X";
        String ASSET = "A";
        String LIABILITY = "L";
        String EQUITY = "E";
    }

}
