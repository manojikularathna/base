package org.army.common.accounting.bl.impl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.bl.TransactionBL;
import org.army.common.accounting.common.AccountingException;
import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.LedgerAccountEntry;
import org.army.common.accounting.entity.TransactionType;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
                        .message(AccountingInternalConstants.ErrorCode.CASH_BOOK_INVALID)
                        .build();
            }

            TransactionType transactionType = commonDao.get(TransactionType.class, transactionTO.getTransactionTypeId());
            if (transactionType == null) {
                throw AccountingException.builder()
                        .message(AccountingInternalConstants.ErrorCode.TRANSACTION_TYPE_INVALID)
                        .build();
            }

            CashBookEntry cashBookEntry = new CashBookEntry();
            cashBookEntry.setAmount(transactionTO.getAmount());
            cashBookEntry.setDate(transactionTO.getDate());
            cashBookEntry.setCashBook(cashBook);
            cashBookEntry.setTransactionType(transactionType);
            cashBookEntry.setStatus(AccountingInternalConstants.Status.ACTIVE);

            List<LedgerAccountEntry> ledgerAccountEntries = new ArrayList<>();
            transactionType.getLedgerAccounts()
                    .forEach(ledgerAccount -> {
                        LedgerAccountEntry ledgerAccountEntry = new LedgerAccountEntry();
                        ledgerAccountEntry.setLedgerAccount(ledgerAccount);
                        ledgerAccountEntry.setCashBookEntry(cashBookEntry);
                        ledgerAccountEntry.setStatus(AccountingInternalConstants.Status.ACTIVE);

                        ledgerAccountEntries.add(ledgerAccountEntry);
                    });
            cashBookEntry.setLedgerAccountEntries(ledgerAccountEntries);

            commonDao.add(cashBookEntry);

            response.setSuccess(true);
        } catch (AccountingException e) {
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        } catch (Exception e) {
            response.setMessage(AccountingInternalConstants.ErrorCode.ERROR);
            response.setSuccess(false);
        }

        return response;
    }
}
