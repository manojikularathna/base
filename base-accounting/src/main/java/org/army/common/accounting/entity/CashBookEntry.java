package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class CashBookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashBookEntryId;

    private Date date;

    private BigDecimal amount;

    private CashBook cashBook;

    private TransactionType transactionType;

    private TransactionType transactionSubType;

    private List<LedgerAccountEntry> ledgerAccountEntries;

    private String status;
}