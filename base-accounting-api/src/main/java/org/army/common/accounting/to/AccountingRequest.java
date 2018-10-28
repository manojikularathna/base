package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.base.common.to.BaseRequest;
import org.army.common.accounting.to.common.OrganizationTO;

@Setter
@Getter
public class AccountingRequest<T> extends BaseRequest {

    private OrganizationTO organization;

    private T payload;

}
