package org.army.common.accounting.to.finalaccount;

import java.math.BigDecimal;
import java.util.List;

public class FinalAccountsItemGroupTO extends FinalAccountsItemTO {

    private String groupName;

    private List<FinalAccountsItemTO> items;

    private String balanceName;

    private BigDecimal balance;
}
