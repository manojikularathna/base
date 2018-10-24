package org.army.common.accounting.to.finalaccount;

import java.math.BigDecimal;
import java.util.List;

public class FinalAccountsItemGroup extends FinalAccountsItem {

    private String groupName;

    private List<FinalAccountsItem> items;

    private String balanceName;

    private BigDecimal balance;
}
