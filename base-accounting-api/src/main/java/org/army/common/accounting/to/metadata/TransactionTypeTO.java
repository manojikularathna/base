package org.army.common.accounting.to.metadata;

import java.util.List;

public class TransactionTypeTO {

    private String transactionName;

    private String description;

    private String transactionCategory;

    private List<LedgerAccountTO> ledgerAccounts;

    private List<TransactionTypeTO> subTypes;

}
