package org.army.common.accounting.to.ledger;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class LedgerAccountTO {

    private String accountName;

    private BigDecimal openingBalance;

    private List<LedgerAccountEntryTO> entries;

    private BigDecimal closingBalance;

}
