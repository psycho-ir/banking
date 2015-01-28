package com.samenea.banking.simia.repository;

import com.samenea.banking.simia.model.Deposit;
import com.samenea.banking.simia.model.repository.DepositRepository;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 6:16 PM
 */
@TransactionConfiguration(transactionManager="simiaTransactionManager", defaultRollback=true)
@ContextConfiguration("classpath:context.xml")
public class DepositRepositoryJDBCTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private DepositRepository repository;

    @Test
    public void should_return_record_one_deposit() {
        final List<? extends Deposit> activeDeposits = (List<? extends Deposit>) repository.findActiveDeposits("681988851");
        Assert.assertEquals(2, activeDeposits.size());
    }

/*    @Test
    @Rollback(false)
    public void should_get_new_seq_number() {
        repository.getNewSeqNumber("300", "1391");
    }*/

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_deposit_does_not_exist() {
        repository.isDepositValidForCharging("1");
    }

    @Test
    public void isDepositValidForCharging_should_return_false() {
        final Boolean depositValidForCharging = repository.isDepositValidForCharging("3464215610.57");
        Assert.assertFalse(depositValidForCharging);

    }

    @Test
    public void isDepositValidForCharging_should_return_true() {
        final Boolean depositValidForCharging = repository.isDepositValidForCharging("3461215593.58");
        Assert.assertTrue(depositValidForCharging);
    }

}
