package org.army.common.accounting.to.finalaccount;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.PeriodTO;
import org.army.common.accounting.to.ValueTO;

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
