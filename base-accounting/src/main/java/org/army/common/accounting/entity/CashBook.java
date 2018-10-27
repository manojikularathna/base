package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class CashBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashBookId;

    private String cashBookName;

    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cashBook")
    private CashBookBalance openingBalance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cashBook")
    private List<CashBookEntry> entries;
}