package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "ledger_account_id")
    private LedgerAccountBalance openingBalance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "attached_account_id")
    private LedgerAccount attachedAccount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attachedAccount")
    private List<LedgerAccount> attachments;

    private String status;

}