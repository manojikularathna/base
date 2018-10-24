package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
public class CashBookBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashBookBalanceId;

    private Date date;

    private BigDecimal balance;
}