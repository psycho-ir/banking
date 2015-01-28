package com.samenea.banking.simia.model;

import com.samenea.banking.deposit.ChargeException;
import com.samenea.banking.simia.model.SimiaMessageHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 1:21 PM
 */

public class SimiaMessageHandlerTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_show_throw_exception_with_null_parameters() {
        SimiaMessageHandler handler = new SimiaMessageHandler(null, ChargeException.class);
    }

    @Test
    public void should_not_throw_exception_when_sucess_is_1() throws ChargeException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PRM_SUCCESS", "1");
        SimiaMessageHandler handler = new SimiaMessageHandler(parameters, ChargeException.class);
        handler.handle();
    }

    @Test(expected = ChargeException.class)
    public void handle_should_throw_exception_when_success_is_not_1() throws ChargeException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PRM_SUCCESS", "-5054");
        parameters.put("PRM_STRERROR", "error!");
        SimiaMessageHandler handler = new SimiaMessageHandler(parameters, ChargeException.class);

        handler.handle();

    }
}
