package org.army.common.accounting.dao.impl;

import org.army.common.accounting.AccountingConstants;
import org.army.common.accounting.common.AccountingInternalConstants;
import org.army.common.accounting.dao.AccountsGenerateDao;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.LedgerAccount;
import org.army.common.accounting.entity.LedgerAccountEntry;
import org.army.common.accounting.to.common.OrganizationTO;
import org.army.common.accounting.to.common.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class AccountsGenerateDaoImpl implements AccountsGenerateDao {

    @Autowired
    private EntityManager entityManager;

    public List<CashBook> getCashBooks(OrganizationTO organization) {

        List<CashBook> cashBooks = entityManager.createQuery(" select cb from CashBook cb where cb.organization = :organization and cb.status = :status ")
                .setParameter("organization", organization.getOrganization())
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .getResultList();

        return cashBooks;
    }

    public List<CashBookEntry> getCashBookEntries(Long cashBookId, Range<Date> range) {

        List<CashBookEntry> cashBooksEntries = entityManager
                .createQuery(" select  e from CashBookEntry e where e.cashBook.cashBookId = :cashBookId and e.status = :status " +
                        " and e.date >= :fromDate and e.date <= :toDate and e.transactionType.transactionCategory in ( :categories ) order by e.date ")
                .setParameter("cashBookId", cashBookId)
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .setParameter("fromDate", range.getFrom())
                .setParameter("toDate", range.getTo())
                .setParameter("categories", Arrays.asList(AccountingConstants.TransactionCategory.CREDIT, AccountingConstants.TransactionCategory.DEBIT))
                .getResultList();

        return cashBooksEntries;
    }

    public BigDecimal getPreviousDayBookTransactionsSum(Long cashBookId, Date start) {

        BigDecimal sum = (BigDecimal) entityManager
                .createQuery("select sum (e.amount) from CashBookEntry e where e.cashBook.cashBookId = :cashBookId and e.status = :status " +
                        " and e.date < :fromDate and e.transactionType.transactionCategory in ( :categories ) ")
                .setParameter("cashBookId", cashBookId)
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .setParameter("fromDate", start)
                .setParameter("categories", Arrays.asList(AccountingConstants.TransactionCategory.CREDIT, AccountingConstants.TransactionCategory.DEBIT))
                .getSingleResult();

        if (sum == null) {
            sum = BigDecimal.ZERO;
        }

        return sum;
    }

    public List<LedgerAccount> getLedgerAccounts(OrganizationTO organization) {

        List<LedgerAccount> ledgerAccounts = entityManager.createQuery(" select l from LedgerAccount l where l.organization = :organization and l.status = :status ")
                .setParameter("organization", organization.getOrganization())
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .getResultList();

        return ledgerAccounts;
    }

    public List<LedgerAccountEntry> getLedgerAccountEntries(Long ledgerAccountId, Range<Date> range) {

        List<LedgerAccountEntry> ledgerAccountEntries = entityManager
                .createQuery(" select  e from LedgerAccountEntry e where e.ledgerAccount.ledgerAccountId = :ledgerAccountId and e.status = :status " +
                        " and e.cashBookEntry.date >= :fromDate and e.cashBookEntry.date <= :toDate order by e.cashBookEntry.date ")
                .setParameter("ledgerAccountId", ledgerAccountId)
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .setParameter("fromDate", range.getFrom())
                .setParameter("toDate", range.getTo())
                .getResultList();

        return ledgerAccountEntries;
    }

    public BigDecimal getPreviousLedgerTransactionsSum(Long ledgerAccountId, Date start) {

        BigDecimal sum = (BigDecimal) entityManager
                .createQuery("select sum (e.cashBookEntry.amount) from LedgerAccountEntry e where e.ledgerAccount.ledgerAccountId = :ledgerAccountId and e.status = :status " +
                        " and e.cashBookEntry.date < :fromDate ")
                .setParameter("ledgerAccountId", ledgerAccountId)
                .setParameter("status", AccountingInternalConstants.Status.ACTIVE)
                .setParameter("fromDate", start)
                .getSingleResult();

        if (sum == null) {
            sum = BigDecimal.ZERO;
        }

        return sum;
    }

    public Map<Long, BigDecimal> getLedgerTransactionsSum(List<Long> ledgerAccountIds, Range<Date> range) {
        return null;
    }

    public Map<Long, BigDecimal> getLedgerTransactionsSum(List<Long> ledgerAccountIds, Date end) {
        return null;
    }

}
