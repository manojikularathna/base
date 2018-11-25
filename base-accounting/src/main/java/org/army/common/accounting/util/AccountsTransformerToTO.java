package org.army.common.accounting.util;

import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.to.metadata.LedgerAccountTO;

public class AccountsTransformerToTO {

    public static LedgerAccountTO toLedgerAccountTO(LedgerAccount ledgerAccount) {
        LedgerAccountTO ledgerAccountTO = new LedgerAccountTO();
        ledgerAccountTO.setAccountName(ledgerAccount.getAccountName());
        ledgerAccountTO.setDescription(ledgerAccount.getDescription());

        return ledgerAccountTO;
    }

}
