package org.army.common.accounting.to.ledger;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class LedgerAccountAmendmentTO {

    private BigDecimal broughtForward;

    private BigDecimal amendment;

    private BigDecimal carriedForward;
}
