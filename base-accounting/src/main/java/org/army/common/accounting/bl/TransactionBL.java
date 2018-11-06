package org.army.common.accounting.bl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;

public interface TransactionBL {

    BaseResponse submit(AccountingRequest<TransactionTO> transaction);

    BaseResponse aggregate(AccountingRequest<TransactionTO> transaction);

}
