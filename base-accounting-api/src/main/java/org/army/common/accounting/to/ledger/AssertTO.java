package org.army.common.accounting.to.ledger;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.common.PeriodTO;
import org.army.common.accounting.to.common.ValueTO;

import java.util.Date;

@Setter
@Getter
public class AssertTO {

    private String description;

    private Date purchasedDate;

    private PeriodTO age;

    private Integer quantity;

    private ValueTO purchase;

}
