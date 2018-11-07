package org.army.common.accounting.bl.impl.generate;

import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.to.cashbook.CashBookTO;
import org.army.common.accounting.to.common.EntryTO;
import org.army.common.accounting.to.common.GeneratePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CashBookGenerator {

    @Autowired
    private AccountsGenerateDao accountsGenerateDao;


    public CashBookTO generateCashBookTO(CashBook cashBook, GeneratePeriod generatePeriod) {

        CashBookTO cashBookTO = new CashBookTO();

        BigDecimal openingBalance = BigDecimal.ZERO;
        if (cashBook.getOpeningBalance() != null) {
            openingBalance = cashBook.getOpeningBalance().getBalance();
        }

        openingBalance = openingBalance.add(getPreviousTransactionsSum(cashBook.getCashBookId(), generatePeriod.getFrom()));

        AtomicReference<BigDecimal> runningBalance = new AtomicReference<>(openingBalance);
        List<CashBookEntry> cashBookEntries = accountsGenerateDao.getCashBookEntries(cashBook.getCashBookId(), generatePeriod);

        cashBookEntries.sort((e1, e2) -> {
            if (!e1.getDate().equals(e2.getDate())) {
                return e1.getDate().compareTo(e2.getDate());
            } else if (!e1.getTransactionType().getTransactionCategory().equals(e2.getTransactionType().getTransactionCategory())) {
                return AccountingInternalConstants.EntriesOrder.CASH_BOOK_ENTRIES.get(e1.getTransactionType().getTransactionCategory())
                        .compareTo(AccountingInternalConstants.EntriesOrder.CASH_BOOK_ENTRIES.get(e2.getTransactionType().getTransactionCategory()));
            } else {
                return 0;
            }
        });

        cashBookTO.setName(cashBook.getCashBookName());
        cashBookTO.setOpeningBalance(openingBalance);

        List<EntryTO> entries = new ArrayList<>();
        cashBookEntries
                .forEach(cashBookEntry -> {

                    EntryTO entryTO = new EntryTO();
                    entryTO.setDate(cashBookEntry.getDate());
                    entryTO.setDescription(cashBookEntry.getTransactionType().getDescription());
                    entryTO.setAmount(cashBookEntry.getAmount());

                    runningBalance.set(runningBalance.get().add(cashBookEntry.getAmount()));
                    entryTO.setRunningBalance(runningBalance.get());

                    entries.add(entryTO);
                });

        cashBookTO.setEntries(entries);
        cashBookTO.setClosingBalance(runningBalance.get());

        return cashBookTO;
    }

    private BigDecimal getPreviousTransactionsSum(Long cashBookId, Date start) {
        return accountsGenerateDao.getPreviousDayBookTransactionsSum(cashBookId, start);
    }
}
