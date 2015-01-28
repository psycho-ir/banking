package com.samenea.banking.simia.service.loan.payment;


import com.samenea.banking.simia.model.InstallmentStatus;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 4:41 PM
 */
/*
@TransactionConfiguration(transactionManager = "simiaTransactionManager", defaultRollback = true)
@ContextConfiguration("classpath:context.xml")
public class SpringInstallmentFinderSelectorIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private InstallmentPaymentSelector installmentFinderSelector;

    @Test
    public void should_select_SAR_RESID_InstallmentFinder() {
        final InstallmentPayment installmentPayment = installmentFinderSelector.findInstallmentPayment(InstallmentStatus.SAR_RESID);
        Assert.assertTrue(installmentPayment instanceof SarResidInstallmentPayment);
    }

    @Test
    public void should_select_MOAVAGH_InstallmentFinder() {
        final InstallmentPayment installmentPayment = installmentFinderSelector.findInstallmentPayment(InstallmentStatus.MOAVAGH);
        Assert.assertTrue(installmentPayment instanceof MoavaghInstallmentPayment);
    }

    @Test
    public void should_select_NORMAL_InstallmentFinder() {
        final InstallmentPayment installmentPayment = installmentFinderSelector.findInstallmentPayment(InstallmentStatus.NORMAL);
        Assert.assertTrue(installmentPayment instanceof NormalInstallmentPayment);
    }
}
*/
