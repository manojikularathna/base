package org.army.common.accounting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class FinalAccountElementGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "element_group_id")
    private Long elementGroupId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_element_group_id")
    private FinalAccountElementGroup parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<FinalAccountElementGroup> subGroups;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "element_group_id")
    private List<FinalAccountElement> elements;

    private String groupHeader;

    private String groupFooter;
}
