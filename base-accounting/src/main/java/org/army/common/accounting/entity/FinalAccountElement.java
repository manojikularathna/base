package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.entity.LedgerAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class FinalAccountElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long elementId;

    private LedgerAccount ledgerAccount;
}
