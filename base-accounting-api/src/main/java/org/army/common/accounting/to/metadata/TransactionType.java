package org.army.common.accounting.to.metadata;

import java.util.List;

public class TransactionType {

    private String transactionName;

    private String description;

    private String transactionCategory;

    private List<LedgerAccount> ledgerAccounts;

    private List<TransactionSubType> subTypes;

}
