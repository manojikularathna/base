package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@Entity
public class LedgerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ledgerAccountId;

    private String accountName;

    private String description;

    private String ledgerCategory;

    private LedgerAccount attachedAccount;

    private List<LedgerAccount> attachments;

    private String status;

}