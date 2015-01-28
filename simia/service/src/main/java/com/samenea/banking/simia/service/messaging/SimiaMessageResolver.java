package com.samenea.banking.simia.service.messaging;

import beh.model.ClsErrorHandlerOra;
import com.samenea.banking.FinancialTransactionException;
import com.samenea.banking.messaging.MessageResolver;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/12/13
 * Time: 11:50 AM
 */
@Service
public class SimiaMessageResolver implements MessageResolver {
    private static final ClsErrorHandlerOra simiaErrorHandler = new ClsErrorHandlerOra();


    @Override
    public String resolve(String code, String message) {
        return simiaErrorHandler.pubFunBlnGetErrorMessage(Integer.valueOf(code), message, message, message);
    }

    @Override
    public String resolve(FinancialTransactionException exception) {
        return this.resolve(exception.getCode(), exception.getMessage());
    }
}
