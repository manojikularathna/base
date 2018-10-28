package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class AccountingOrganizationalEntity extends AccountingEntity {

    private String organization;

}
