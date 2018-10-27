package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Entity
public class LedgerAccountBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ledgerAccountBalanceId;

    private Date date;

    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ledger_account_id")
    private LedgerAccount ledgerAccount;
}