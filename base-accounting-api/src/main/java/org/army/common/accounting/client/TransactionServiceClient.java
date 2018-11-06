package org.army.common.accounting.client;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "transaction", url = "http://localhost:8080/transaction")
public interface TransactionServiceClient {

    @RequestMapping(path = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse submit(@RequestBody AccountingRequest<TransactionTO> transaction);

    @RequestMapping(path = "/aggregate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse aggregate(@RequestBody AccountingRequest<TransactionTO> transaction);
}
