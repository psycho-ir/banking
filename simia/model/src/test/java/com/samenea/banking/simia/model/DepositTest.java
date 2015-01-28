package com.samenea.banking.simia.model;

import org.junit.Test;

/**
 * @author : Soroosh Sarabadani
 *         Date: 1/16/13
 *         Time: 4:37 PM
 */

public class DepositTest {
    private String description = "DESC";
    private Long remainedAmount = 1000L;
    private String customerCode = "C-100";
    private String depositNumber = "D-100";


    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_exception_when_depositNumber_is_null() {
        new Deposit(null, description, customerCode, remainedAmount,"300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_exception_when_depositNumber_is_empty() {
        new Deposit("", description, customerCode, remainedAmount,"300");
    }


    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_exception_when_customerCode_is_null() {
        new Deposit(depositNumber, description, null, remainedAmount,"300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_exception_when_customerCode_is_empty() {
        new Deposit(depositNumber, description, "", remainedAmount,"300");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throw_exception_when_remainedAmount_is_null() {
        new Deposit(depositNumber, description, customerCode, null,"300");
    }


}
