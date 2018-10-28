package org.army.common.accounting;

public interface AccountingConstants {

    interface TransactionCategory {
        String CREDIT = "C";
        String DEBIT = "D";
    }

    interface LedgerCategory {
        String INCOME = "I";
        String EXPENSE = "X";
        String ASSET = "A";
        String LIABILITY = "L";
        String EQUITY = "E";
    }

    interface FinalAccountType {
        String PROFIT_AND_LOSS_ACCOUNT = "PL";
        String BALANCE_SHEET = "BS";
    }
}
