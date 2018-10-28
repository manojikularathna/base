package org.army.common.accounting.to;

import lombok.Getter;
import lombok.Setter;
import org.army.common.accounting.to.cashbook.CashBookTO;

import java.util.List;

@Setter
@Getter
public class DayBookResponse extends AccountsGenerateResponse {

    private List<CashBookTO> cashBooks;

}
