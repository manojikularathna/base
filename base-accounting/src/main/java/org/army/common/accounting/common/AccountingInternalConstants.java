package org.army.common.accounting.common;

import org.army.common.accounting.AccountingConstants;

import java.util.HashMap;
import java.util.Map;

public interface AccountingInternalConstants {

    abstract class EntriesOrder {

        public  static  final Map<String, Integer> CASH_BOOK_ENTRIES;

        static {
            CASH_BOOK_ENTRIES = new HashMap<>();
            CASH_BOOK_ENTRIES.put(AccountingConstants.TransactionCategory.DEBIT, 1);
            CASH_BOOK_ENTRIES.put(AccountingConstants.TransactionCategory.CREDIT, 2);
        }

    }

    interface Status {
        String ACTIVE = "ACT";
        String INACTIVE = "INA";
    }

    interface ErrorCode {
        String ERROR = "error";

        String CASH_BOOK_EMPTY = "cash-book.empty";
        String CASH_BOOK_INVALID = "cash-book.invalid";
        String TRANSACTION_TYPE_EMPTY = "transaction-type.empty";
        String TRANSACTION_TYPE_INVALID = "transaction-type.invalid";
    }
}
