package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class CashBookEntry extends AccountingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashBookEntryId;

    private Date date;

    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cash_book_id")
    private CashBook cashBook;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cashBookEntry")
    private List<LedgerAccountEntry> ledgerAccountEntries;

    private String status;
}