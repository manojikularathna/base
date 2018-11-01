package org.army.common.accounting.util;

import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.entity.LedgerAccountBalance;
import org.army.common.accounting.to.common.GeneratePeriod;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AccountGenerateUtil {

    public static BigDecimal getApplicableBalance(List<LedgerAccountBalance> balances, GeneratePeriod period) {

        BigDecimal ledgerAccountBalance;
        Optional<LedgerAccountBalance> processedBalance = balances
                .stream()
                .filter(balance -> AccountingInternalConstants.Status.ACTIVE.equals(balance.getStatus()) &&
                        balance.getDate().before(period.getTo()) || balance.getDate().equals(period.getTo()))
                .sorted(Comparator.comparing(LedgerAccountBalance::getDate).reversed())
                .findFirst();

        if (processedBalance.isPresent()) {
            ledgerAccountBalance = processedBalance.get()
                    .getBalance();
        } else {
            ledgerAccountBalance = BigDecimal.ZERO;
        }

        return ledgerAccountBalance;
    }
}
