package org.army.common.accounting.api;

import org.army.base.common.to.BaseResponse;
import org.army.base.common.to.Transition;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;

public interface TransactionService {

    BaseResponse submit(AccountingRequest<TransactionTO>)
}
