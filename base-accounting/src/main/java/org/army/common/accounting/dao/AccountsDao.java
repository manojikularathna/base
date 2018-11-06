package org.army.common.accounting.dao;

import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.TransactionType;
import org.army.common.accounting.to.common.OrganizationTO;

import java.util.List;

public interface AccountsDao {

    CashBook getCashBook(String cashBookCode, OrganizationTO organization);

    TransactionType getTransactionType(String transactionCode, OrganizationTO organization);

    List<CashBookEntry> getPreviousEntries(String transactionCode, OrganizationTO organization);
}
