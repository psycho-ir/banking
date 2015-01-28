package com.samenea.banking.customer;

/**
 * An abstract base model for customers of core bankings.
 * clients use this model for accessing to customer objects of core banking.
 */
public interface ICustomer {
    String getName();

    String getLastName();

    String getCustomerCode();

    Boolean isUserLock();

}
