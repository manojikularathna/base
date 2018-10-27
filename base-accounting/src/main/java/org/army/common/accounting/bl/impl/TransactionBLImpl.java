package org.army.common.accounting.bl.impl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.bl.TransactionBL;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionBLImpl implements TransactionBL {

    @Autowired
    private CommonDAO commonDao;


    public BaseResponse submit(AccountingRequest<TransactionTO> transaction) {return null;}
}
