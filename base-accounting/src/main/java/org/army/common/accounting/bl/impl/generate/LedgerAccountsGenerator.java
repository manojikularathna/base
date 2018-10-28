package org.army.common.accounting.bl.impl.generate;

import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.entity.LedgerAccountEntry;
import org.army.common.accounting.to.common.GeneratePeriod;
import org.army.common.accounting.to.ledger.LedgerAccountEntryTO;
import org.army.common.accounting.to.ledger.LedgerAccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LedgerAccountsGenerator {

    @Autowired
    private AccountsGenerateDao accountsGenerateDao;

    public LedgerAccountTO generateLedgerAccountTO(LedgerAccount ledgerAccount, GeneratePeriod generatePeriod) {

        LedgerAccountTO ledgerAccountTO = new LedgerAccountTO();

        BigDecimal openingBalance = BigDecimal.ZERO;
        if (ledgerAccount.getOpeningBalance() != null) {
            openingBalance = ledgerAccount.getOpeningBalance().getBalance();
        }

        openingBalance = openingBalance.add(getPreviousTransactionsSum(ledgerAccount.getLedgerAccountId(), generatePeriod.getFrom()));

        AtomicReference<BigDecimal> runningBalance = new AtomicReference<>(openingBalance);
        List<LedgerAccountEntry> ledgerAccountEntries = accountsGenerateDao.getLedgerAccountEntries(ledgerAccount.getLedgerAccountId(), generatePeriod);

        ledgerAccountTO.setAccountName(ledgerAccount.getAccountName());
        ledgerAccountTO.setOpeningBalance(openingBalance);

        List<LedgerAccountEntryTO> ledgerAccountEntryTOs = new ArrayList<>();
        ledgerAccountEntries
                .forEach(ledgerAccountEntry -> {
                    LedgerAccountEntryTO ledgerAccountEntryTO = new LedgerAccountEntryTO();

                    ledgerAccountEntryTOs.add(ledgerAccountEntryTO);
                });

        ledgerAccountTO.setEntries(ledgerAccountEntryTOs);
        ledgerAccountTO.setClosingBalance(runningBalance.get());

        return ledgerAccountTO;
    }

    private BigDecimal getPreviousTransactionsSum(Long cashBookId, Date start) {
        return accountsGenerateDao.getPreviousLedgerTransactionsSum(cashBookId, start);
    }
}
