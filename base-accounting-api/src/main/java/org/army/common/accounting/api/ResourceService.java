package org.army.common.accounting.api;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;

public interface ResourceService {

    BaseResponse changeValuation(AccountingRequest<TransactionTO> request);

}
