package org.army.common.accounting.dao;

import org.army.common.accounting.entity.*;
import org.army.common.accounting.to.common.OrganizationTO;
import org.army.base.common.to.Range;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountsGenerateDao {

    List<CashBook> getCashBooks(OrganizationTO organization);

    List<CashBookEntry> getCashBookEntries(Long cashBookId, Range<Date> range);

    BigDecimal getPreviousDayBookTransactionsSum(Long cashBookId, Date start);

    List<LedgerAccount> getLedgerAccounts(OrganizationTO organization);

    List<LedgerAccountEntry> getLedgerAccountEntries(Long ledgerAccountId, Range<Date> range);

    BigDecimal getPreviousLedgerTransactionsSum(Long cashBookId, Date start);

    FinalAccountStructure getFinalAccountStructure(OrganizationTO organization, String finalAccountType);

    Map<Long, BigDecimal> getLedgerTransactionsSum(List<Long> ledgerAccountIds, Range<Date> range);

    Map<Long, BigDecimal> getLedgerTransactionsSum(List<Long> ledgerAccountIds, Date end);

}
