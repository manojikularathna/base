package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cash_book_id")
    private CashBook cashBook;
}