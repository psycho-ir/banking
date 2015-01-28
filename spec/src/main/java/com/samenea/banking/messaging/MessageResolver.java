package com.samenea.banking.messaging;

import com.samenea.banking.FinancialTransactionException;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/12/13
 * Time: 11:47 AM
 */
public interface MessageResolver {
    String resolve(String code, String message);

    String resolve(FinancialTransactionException exception);
}
