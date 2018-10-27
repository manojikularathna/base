package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class LedgerAccountEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ledgerAccountEntryId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ledger_account_id")
    private LedgerAccount ledgerAccount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cash_book_entry_id")
    private CashBookEntry cashBookEntry;

    private String status;


}
