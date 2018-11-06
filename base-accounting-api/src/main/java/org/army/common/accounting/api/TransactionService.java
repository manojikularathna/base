package org.army.common.accounting.api;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;

public interface TransactionService {

    BaseResponse submit(AccountingRequest<TransactionTO> transaction);

    BaseResponse aggregate(AccountingRequest<TransactionTO> transaction);
}
