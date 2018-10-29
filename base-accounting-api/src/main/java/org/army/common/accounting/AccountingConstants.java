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
        String NONE = "N";
    }

    interface FinalAccountType {
        String PROFIT_AND_LOSS_ACCOUNT = "PL";
        String BALANCE_SHEET = "BS";

        interface Group {
            String UNCLASSIFIED = "UNCLASSIFIED";
        }
    }

}
