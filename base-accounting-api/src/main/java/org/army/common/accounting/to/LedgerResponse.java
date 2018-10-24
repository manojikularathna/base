package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.ledger.LedgerAccountTO;

import java.util.List;

@Setter
@Getter
public class LedgerResponse extends AccountsGenerateResponse {

    private List<LedgerAccountTO> ledgers;

}
