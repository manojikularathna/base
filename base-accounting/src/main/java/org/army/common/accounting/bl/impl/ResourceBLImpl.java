package org.army.common.accounting.bl.impl;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.bl.ResourceBL;
import org.army.common.accounting.common.AccountingException;
import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.dao.AccountsDao;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.LedgerAccountBalance;
import org.army.common.accounting.entity.TransactionType;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.army.common.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceBLImpl implements ResourceBL {

    @Autowired
    private AccountsDao accountsDao;

    @Autowired
    private CommonDAO commonDAO;

    public BaseResponse changeValuation(AccountingRequest<TransactionTO> request) {

        BaseResponse response = new BaseResponse();
        TransactionTO transactionTO = request.getPayload();

        try {
            CashBook cashBook = accountsDao.getCashBook(transactionTO.getCashBookCode(), request.getOrganization());
            if (cashBook == null) {
                throw AccountingException.builder()
                        .message(AccountingInternalConstants.ErrorCode.CASH_BOOK_INVALID)
                        .build();
            }

            TransactionType transactionType = accountsDao.getTransactionType(transactionTO.getTransactionTypeCode(), request.getOrganization());
            if (transactionType == null) {
                throw AccountingException.builder()
                        .message(AccountingInternalConstants.ErrorCode.TRANSACTION_TYPE_INVALID)
                        .build();
            }

            transactionType.getLedgerAccounts()
                    .forEach(ledgerAccount -> {
                        ledgerAccount.getOpeningBalance()
                                .stream()
                                .filter(ledgerAccountBalance -> ledgerAccountBalance.getDate().equals(transactionTO.getDate()))
                                .forEach(ledgerAccountBalance -> ledgerAccountBalance.setStatus(AccountingInternalConstants.Status.INACTIVE));

                        LedgerAccountBalance ledgerAccountBalance = new LedgerAccountBalance();
                        ledgerAccountBalance.setLedgerAccount(ledgerAccount);
                        ledgerAccountBalance.setBalance(transactionTO.getAmount());
                        ledgerAccountBalance.setDate(transactionTO.getDate());
                        ledgerAccountBalance.setStatus(AccountingInternalConstants.Status.ACTIVE);

                        ledgerAccount.getOpeningBalance().add(ledgerAccountBalance);
                    });

            commonDAO.update(transactionType);

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
