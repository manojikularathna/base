package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.finalaccount.FinalAccountsItemTO;

import java.util.List;

@Setter
@Getter
public class FinalAccountResponse extends AccountsGenerateResponse {

    private String accountType;

    private List<FinalAccountsItemTO> items;
}
