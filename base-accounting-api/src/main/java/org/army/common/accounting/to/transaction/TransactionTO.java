package org.army.common.accounting.to.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class TransactionTO {

    private Date date;

    private BigDecimal amount;

    private Long transactionTypeId;

    private Long cashBookId;

}
