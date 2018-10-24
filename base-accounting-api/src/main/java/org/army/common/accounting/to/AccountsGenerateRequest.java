package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.common.GeneratePeriod;

@Setter
@Getter
public class AccountsGenerateRequest {

    private GeneratePeriod period;
}
