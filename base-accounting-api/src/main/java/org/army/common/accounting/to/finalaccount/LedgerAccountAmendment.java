package org.army.common.accounting.to.finalaccount;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class LedgerAccountAmendment {

    private BigDecimal broughtForward;

    private BigDecimal amendment;

    private BigDecimal carriedForward;
}
