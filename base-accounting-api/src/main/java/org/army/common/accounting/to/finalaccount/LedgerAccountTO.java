package org.army.common.accounting.to.finalaccount;

import org.army.common.accounting.to.common.GeneratePeriod;
import org.army.common.accounting.to.common.EntryTO;

import java.util.Date;
import java.util.List;

public class LedgerAccountTO {

    private String accountName;

    private Date date;

    private GeneratePeriod generatePeriod;

    private List<EntryTO> entries;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public GeneratePeriod getGeneratePeriod() {
        return generatePeriod;
    }

    public void setGeneratePeriod(GeneratePeriod generatePeriod) {
        this.generatePeriod = generatePeriod;
    }

    public List<EntryTO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryTO> entries) {
        this.entries = entries;
    }
}
