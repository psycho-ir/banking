package com.samenea.banking.deposit;

import com.samenea.banking.FinancialTransactionException;

/**
 * This exception may be occured in charging a depost.
 * code is core banking depend exception
 *
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 12:56 PM
 */

public class ChargeException extends FinancialTransactionException {
    private final static String MESSAGE = "Error in charging. Details: [%s]";

    public ChargeException(String code, String message) {
        super(code, String.format(MESSAGE, message));
    }
}
