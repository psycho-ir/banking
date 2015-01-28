package com.samenea.banking.simia.model;

import com.samenea.banking.FinancialTransactionException;
import com.samenea.commons.component.utils.exceptions.SamenRuntimeException;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 1:17 PM
 */

public class SimiaMessageHandler {

    private final Class<? extends FinancialTransactionException> exceptionType;


    public static final String SUCCESS = "1";
    private Map<String, Object> parameters;
    private final String PRM_SUCCESS = "PRM_SUCCESS";
    private final String PRM_STRERROR = "PRM_STRERROR";
    private final String PRM_ERRORMSG = "PRM_ERRORMSG";


    public SimiaMessageHandler(Map<String, Object> parameters, Class<? extends FinancialTransactionException> exceptionType) {
        Assert.notNull(parameters, "parameters cannot be null.");
        this.parameters = parameters;
        this.exceptionType = exceptionType;
    }

    public void handle() {
        final String code = parameters.get(PRM_SUCCESS).toString();
        if (code.equals(SUCCESS)) {
            return;
        } else {
            throwException(code, getErrorDescription(code));
        }
    }

    private String getErrorDescription(String code) {
        String description = "";
        if (parameters.get(PRM_STRERROR) == null) {
            description += "Financial transaction failed with code: " + code + " - ";
        } else {
            description += parameters.get(PRM_STRERROR).toString();
        }
        if (parameters.get(PRM_ERRORMSG) != null) {
            description += parameters.get(PRM_ERRORMSG).toString();
        }
        return description;
    }

    private void throwException(String code, String message) throws FinancialTransactionException {
        try {
            throw exceptionType.getConstructor(String.class, String.class).newInstance(code, message);
        } catch (InstantiationException e) {
            throw new SamenRuntimeException("Problem in creating Excepion object.", e);
        } catch (IllegalAccessException e) {
            throw new SamenRuntimeException("Problem in creating Excepion object.", e);
        } catch (InvocationTargetException e) {
            throw new SamenRuntimeException("Problem in creating Excepion object.", e);
        } catch (NoSuchMethodException e) {
            throw new SamenRuntimeException("Problem in creating Excepion object.", e);
        }

    }
}
