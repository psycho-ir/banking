package com.samenea.banking.loan;

import com.samenea.banking.FinancialTransactionException;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/13/13
 * Time: 10:32 AM
 */

public class InstallmentPaymentException extends FinancialTransactionException {
    private static final String MESSAGE = "Error in Installment Payment. Details: [%s]";

    public InstallmentPaymentException(String code, String message) {
        super(code, String.format(MESSAGE, message));
    }
}
