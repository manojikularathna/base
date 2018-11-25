package org.army.common.accounting.service;

import org.army.base.common.to.BaseResponse;
import org.army.common.accounting.api.ResourceService;
import org.army.common.accounting.bl.ResourceBL;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.transaction.TransactionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/resource")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceBL resourceBL;

    @RequestMapping(path = "/valuation", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse changeValuation(@RequestBody AccountingRequest<TransactionTO> request) {
        return resourceBL.changeValuation(request);
    }
}
