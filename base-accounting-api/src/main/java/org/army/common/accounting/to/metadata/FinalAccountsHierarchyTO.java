package org.army.common.accounting.to.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FinalAccountsHierarchyTO {

    private String accountName;

    private List<FinalAccountsElementTO> elements;

}
