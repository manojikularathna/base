package org.army.common.accounting.api;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.ledger.ResourceValuationTO;

public interface ResourceService {

    BaseResponse changeValuation(AccountingRequest<ResourceValuationTO> request);

}
