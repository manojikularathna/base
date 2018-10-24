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
public class CashBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashBookId;

    private String cashBookName;

    private String description;

    private CashBookBalance balance;

    private List<CashBookEntry> entries;
}