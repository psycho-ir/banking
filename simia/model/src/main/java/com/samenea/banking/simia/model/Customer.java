package com.samenea.banking.simia.model;

import com.samenea.banking.customer.ICustomer;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 2:52 PM
 */

public class Customer implements ICustomer,Serializable {
    private final String name;
    private final String lastName;
    private final String customerCode;
    private final Boolean isUserLock;

    public Customer(String name, String lastName, String customerCode, Boolean userLock) {
        Assert.notNull(name, "name cannot be null.");
        Assert.hasText(name, "name cannot be empty.");
        Assert.notNull(lastName, "lastName cannot be null.");
        Assert.hasText(lastName, "lastName cannot be empty.");
        Assert.notNull(customerCode, "customerCode cannot be null.");
        Assert.hasText(customerCode, "customerCode cannot be empty.");
        Assert.notNull(userLock, "userLock cannot be null.");

        this.name = name;
        this.lastName = lastName;
        this.customerCode = customerCode;
        this.isUserLock = userLock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getCustomerCode() {
        return customerCode;
    }

    @Override
    public Boolean isUserLock() {
        return isUserLock;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", isUserLock=" + isUserLock +
                '}';
    }
}
