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
public class FinalAccountStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long structureId;

    private String finalAccountType;


}
