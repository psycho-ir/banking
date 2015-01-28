package com.samenea.banking;

import com.samenea.commons.component.utils.exceptions.SamenRuntimeException;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/13/13
 * Time: 10:44 AM
 */

public class FinancialTransactionException extends SamenRuntimeException {
    private final String code;

    public FinancialTransactionException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
