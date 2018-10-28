package org.army.common.accounting.to.finalaccount;

import lombok.Getter;
import lombok.Setter;
import org.army.base.common.to.BaseRequest;
import org.army.common.accounting.to.AccountingRequest;
import org.army.common.accounting.to.common.GeneratePeriod;

@Setter
@Getter
public class AccountsGenerateTO {

    private GeneratePeriod period;
}
