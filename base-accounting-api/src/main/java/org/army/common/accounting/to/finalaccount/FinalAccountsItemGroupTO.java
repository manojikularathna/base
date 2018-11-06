package org.army.common.accounting.to.finalaccount;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class FinalAccountsItemGroupTO {

    private String groupCode;

    private String groupName;

    private List<FinalAccountsItemGroupTO> groups;

    private List<FinalAccountsItemLedgerTO> ledgers;

    private String balanceName;

    private BigDecimal balance;
}
