package org.army.common.accounting.dao.impl;

import org.army.common.accounting.dao.AccountsDao;
import org.army.common.accounting.entity.CashBook;
import org.army.common.accounting.entity.CashBookEntry;
import org.army.common.accounting.entity.TransactionType;
import org.army.common.accounting.to.common.OrganizationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountsDaoImpl implements AccountsDao {

    @Autowired
    private EntityManager entityManager;

    public CashBook getCashBook(String cashBookCode, OrganizationTO organization) {
        return (CashBook) entityManager
                .createQuery(" select c from CashBook c where c.cashBookCode = :cashBookCode and c.organization = :organization ")
                .setParameter("cashBookCode", cashBookCode)
                .setParameter("organization", organization.getOrganization())
                .getSingleResult();
    }

    public TransactionType getTransactionType(String transactionCode, OrganizationTO organization) {
        return (TransactionType) entityManager
                .createQuery(" select t from TransactionType t where t.transactionCode = :transactionCode and t.organization = :organization ")
                .setParameter("transactionCode", transactionCode)
                .setParameter("organization", organization.getOrganization())
                .getSingleResult();
    }

    public List<CashBookEntry> getPreviousEntries(String transactionCode, OrganizationTO organization) {
        return entityManager
                .createQuery(" select e from CashBookEntry e where e.transactionType.transactionCode = :transactionCode and e.cashBook.organization = :organization ")
                .setParameter("transactionCode", transactionCode)
                .setParameter("organization", organization.getOrganization())
                .getResultList();
    }
}
