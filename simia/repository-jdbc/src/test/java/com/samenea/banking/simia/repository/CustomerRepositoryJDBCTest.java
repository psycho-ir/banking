package com.samenea.banking.simia.repository;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.simia.model.repository.CustomerRepository;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.TransactionManager;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 3:16 PM
 */
@TransactionConfiguration(transactionManager="simiaTransactionManager", defaultRollback=true)
@ContextConfiguration("classpath:context.xml")
public class CustomerRepositoryJDBCTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void should_find_customer() {
        ICustomer customer = customerRepository.findCustomer("63170698", "yousefi63170698", "yousefi006");
        System.out.println(customer.toString());
        Assert.assertNotNull(customer);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_customer_does_not_exist() {
        ICustomer customer = customerRepository.findCustomer("6317069", "yousefi63170698", "yousefi006");

    }

    @Test(expected = NotFoundException.class)
    public void findCustomersOfDeposit_should_throw_exception_when_deposit_number_is_not_true() {
        customerRepository.findCustomersOfDeposit("1");
    }

    @Test
    public void findCustomersOfDeposit_should_return_all_customers_of_deposit() {
        final List<ICustomer> customersOfDeposit = customerRepository.findCustomersOfDeposit("252278630.57");
        Assert.assertEquals(1, customersOfDeposit.size());
    }
}
