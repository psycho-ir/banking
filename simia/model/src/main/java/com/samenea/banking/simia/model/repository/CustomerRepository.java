package com.samenea.banking.simia.model.repository;

import com.samenea.banking.customer.ICustomer;

import java.util.List;

public interface CustomerRepository {
    public ICustomer findCustomer(String customerId, String username, String password);
    public List<ICustomer> findCustomersOfDeposit(String depositNumber);
}
