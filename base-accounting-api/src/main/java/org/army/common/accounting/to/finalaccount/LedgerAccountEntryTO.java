package org.army.common.accounting.to.finalaccount;

import org.army.common.accounting.to.common.EntryTO;

import java.math.BigDecimal;
import java.util.List;

public class LedgerAccountEntryTO extends EntryTO {

    private List<LedgerAccountAmendmentTO> amendments;

    private BigDecimal finalAmount;

}
