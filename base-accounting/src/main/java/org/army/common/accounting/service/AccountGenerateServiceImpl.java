package org.army.common.accounting.service;

import org.army.common.accounting.api.AccountGenerateService;
import org.army.common.accounting.bl.AccountGenerateBL;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.finalaccount.AccountsGenerateTO;
import org.army.common.accounting.to.DayBookResponse;
import org.army.common.accounting.to.FinalAccountResponse;
import org.army.common.accounting.to.LedgerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountGenerateServiceImpl implements AccountGenerateService {

    @Autowired
    private AccountGenerateBL accountGenerateBL;

    @RequestMapping(path = "/day-book", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DayBookResponse generateDayBook(@RequestBody AccountingRequest<AccountsGenerateTO> request) {
        return accountGenerateBL.generateDayBook(request);
    }

    @RequestMapping(path = "/ledger", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LedgerResponse generateLedger(@RequestBody AccountingRequest<AccountsGenerateTO> request) {
        return accountGenerateBL.generateLedger(request);
    }

    @RequestMapping(path = "/profit-and-loss-account", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FinalAccountResponse generateProfitAndLossAccount(@RequestBody AccountingRequest<AccountsGenerateTO> request) {
        return accountGenerateBL.generateProfitAndLossAccount(request);
    }

    @RequestMapping(path = "/balance-sheet", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FinalAccountResponse generateBalanceSheet(@RequestBody AccountingRequest<AccountsGenerateTO> request) {
        return accountGenerateBL.generateBalanceSheet(request);
    }
}
