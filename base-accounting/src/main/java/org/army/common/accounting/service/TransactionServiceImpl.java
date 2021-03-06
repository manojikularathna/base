package org.army.common.accounting.service;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.api.TransactionService;
import org.army.common.accounting.bl.TransactionBL;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/transaction")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionBL transactionBL;

    @RequestMapping(path = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse submit(@RequestBody AccountingRequest<TransactionTO> transaction) {
        return transactionBL.submit(transaction);
    }

    @RequestMapping(path = "/aggregate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse aggregate(@RequestBody AccountingRequest<TransactionTO> transaction) {
        return transactionBL.aggregate(transaction);
    }
}
