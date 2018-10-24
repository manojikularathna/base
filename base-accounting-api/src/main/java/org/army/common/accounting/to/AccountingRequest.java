package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.base.common.to.BaseRequest;

@Setter
@Getter
public class AccountingRequest<T> extends BaseRequest {

    private T payload;

}
