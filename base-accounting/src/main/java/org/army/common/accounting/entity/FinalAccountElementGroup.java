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
public class FinalAccountElementGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long elementGroupId;

    private FinalAccountElementGroup parent;

    private List<FinalAccountElementGroup> subGroups;

    private List<FinalAccountElement> elements;

    private String groupHeader;

    private String groupFooter;
}
