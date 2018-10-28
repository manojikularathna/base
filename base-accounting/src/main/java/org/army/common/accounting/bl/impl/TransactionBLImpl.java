package org.army.common.accounting.bl.impl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.bl.TransactionBL;
import org.army.common.accounting.common.AccountingException;
import org.army.common.accounting.common.AccountsConstants;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionBLImpl implements TransactionBL {

    @Autowired
    private CommonDAO commonDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse submit(AccountingRequest<TransactionTO> transactionRequest) {

        BaseResponse response = new BaseResponse();
        TransactionTO transactionTO = transactionRequest.getPayload();

        try {
            CashBook cashBook = commonDao.get(CashBook.class, transactionTO.getCashBookId());
            if (cashBook == null) {
                throw AccountingException.builder()
                        .message(AccountsConstants.ErrorCode.CASH_BOOK_INVALID)
                        .build();
            }

            CashBookEntry cashBookEntry = new CashBookEntry();
            cashBookEntry.setAmount(transactionTO.getAmount());
            cashBookEntry.setDate(transactionTO.getDate());
            cashBookEntry.setCashBook(cashBook);
            cashBookEntry.setStatus(AccountsConstants.Status.ACTIVE);


            commonDao.add(cashBookEntry);

            response.setSuccess(true);
        } catch (AccountingException e) {
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }

        return response;
    }
}
