package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class LedgerAccountEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ledgerAccountEntryId;

    private LedgerAccount ledgerAccount;

    private CashBookEntry cashBookEntry;

    private String status;


}
