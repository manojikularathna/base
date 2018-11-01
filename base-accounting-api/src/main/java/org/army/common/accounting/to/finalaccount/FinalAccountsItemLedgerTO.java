package org.army.common.accounting.to.finalaccount;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.metadata.LedgerAccountTO;

import java.math.BigDecimal;

@Setter
@Getter
public class FinalAccountsItemLedgerTO {

    private LedgerAccountTO ledgerAccount;

    private BigDecimal balance;

}
