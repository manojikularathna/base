package org.army.common.accounting.bl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;

public interface ResourceBL {

    BaseResponse changeValuation(AccountingRequest<TransactionTO> request);

}
