package org.army.common.accounting.to.cashbook;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.common.EntryTO;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CashBookTO {

    private String name;

    private BigDecimal openingBalance;

    private List<EntryTO> entries;

    private BigDecimal closingBalance;
}
