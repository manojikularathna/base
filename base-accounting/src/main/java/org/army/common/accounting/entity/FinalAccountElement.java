package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class FinalAccountElement extends AccountingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long elementId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ledger_account_id")
    private LedgerAccount ledgerAccount;

    private String operator;
}
