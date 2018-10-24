package org.army.common.accounting.to.finalaccount;

import org.army.common.accounting.to.metadata.LedgerAccountTO;

import java.math.BigDecimal;

public class FinalAccountsItemLedgerTO extends FinalAccountsItemTO {

    private LedgerAccountTO ledgerAccount;

    private BigDecimal balance;

}
