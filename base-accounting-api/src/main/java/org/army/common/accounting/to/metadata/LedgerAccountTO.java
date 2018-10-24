package org.army.common.accounting.to.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LedgerAccountTO {

    private String accountName;

    private String description;

    private String ledgerAccountCategory;

    private List<LedgerAccountTO> attachments;

    private LedgerAccountTO attachedAccount;

}
