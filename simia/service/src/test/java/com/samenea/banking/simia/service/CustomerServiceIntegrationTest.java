package com.samenea.banking.simia.service;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.banking.simia.service.customer.CustomerService;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 3:35 PM
 */

@TransactionConfiguration(transactionManager= SimiaUtils.SIMIA_TRANSACTION_MANAGER, defaultRollback=true)
@ContextConfiguration("classpath:context.xml")
public class CustomerServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CustomerService customerService;


    @Test
    public void should_find_customer() {
        ICustomer customer = customerService.findCustomer("63170698", "yousefi63170698", "yousefi006");
        System.out.println(customer.toString());
        Assert.assertNotNull(customer);
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_customer_does_not_exist() {
        ICustomer customer = customerService.findCustomer("6317069", "yousefi63170698", "yousefi006");

    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_deposit_does_not_exist() {
        customerService.findCustomersOfDeposit("1");
    }

}
