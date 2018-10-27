package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionTypeId;

    private String transactionName;

    private String description;

    private String transactionCategory;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_type_id")
    private TransactionType parentType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parentType")
    private List<TransactionType> subTypes;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_type_ledger_account",
            joinColumns = @JoinColumn(name = "transaction_type_id"),
            inverseJoinColumns = @JoinColumn(name = "ledger_account_id"))
    private List<LedgerAccount> ledgerAccounts;

    private String status;
}
