package org.army.common.accounting.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountingException extends Exception {

    private String message;

}
