package org.army.common.accounting.dao;

import org.army.base.common.to.Range;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.TransactionType;
import org.army.common.accounting.to.common.OrganizationTO;

import java.util.Date;
import java.util.List;

public interface AccountsDao {

    CashBook getCashBook(String cashBookCode, OrganizationTO organization);

    TransactionType getTransactionType(String transactionCode, OrganizationTO organization);

    List<CashBookEntry> getPreviousEntries(String transactionCode, Range<Date> dateRange, OrganizationTO organization);
}
