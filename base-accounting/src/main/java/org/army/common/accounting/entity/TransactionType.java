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
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private String transactionName;

    private String description;

    private String transactionCategory;

    private TransactionType parentType;

    private List<TransactionType> subTypes;

    private List<LedgerAccount> ledgerAccounts;

    private String status;
}
