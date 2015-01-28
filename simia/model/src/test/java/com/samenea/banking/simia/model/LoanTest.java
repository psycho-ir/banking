package com.samenea.banking.simia.model;

import com.samenea.banking.loan.InstallmentStatus;
import com.samenea.commons.component.utils.log.LoggerFactory;
import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/16/13
 * Time: 12:26 PM
 */

public class LoanTest {
    private Logger logger = LoggerFactory.getLogger(LoanTest.class);
    private String loanDescription = "description";
    private long originalAmount;
    private long remainedAmount;

    @Test(expected = IllegalStateException.class)
    public void getPayableInstallment_should_throw_exception() {
        originalAmount = 1000L;
        remainedAmount = 100L;
        Loan l = new Loan("1233", Calendar.getInstance().getTime(), "111", originalAmount, remainedAmount, "111", loanDescription, AghdType.EJARE_BE_SHARTE_TAMLIK);
        l.getPayableInstallment();
    }

    @Test()
    public void getPayableInstallment_should_return_installment() {
        Loan l = new Loan("1233", Calendar.getInstance().getTime(), "111", originalAmount, remainedAmount, "111", loanDescription, AghdType.EJARE_BE_SHARTE_TAMLIK);
        l.setPayableInstallment(new Installment("111", Calendar.getInstance().getTime(), 111L, 111L, 111L));
        Assert.assertNotNull(l.getPayableInstallment());
    }

    @Test
    public void hasPayableInstallment_should_return_true() {
        Loan l = new Loan("1233", Calendar.getInstance().getTime(), "111", originalAmount, remainedAmount, "111", loanDescription, AghdType.EJARE_BE_SHARTE_TAMLIK);
        l.setPayableInstallment(new Installment("111", Calendar.getInstance().getTime(), 111L, 111L, 111L));
        Assert.assertTrue(l.hasPayableInstallment());
    }

    @Test
    public void hasPayableInstallment_should_return_false() {
        Loan l = new Loan("1233", Calendar.getInstance().getTime(), "111", originalAmount, remainedAmount, "111", loanDescription, AghdType.EJARE_BE_SHARTE_TAMLIK);
        Assert.assertFalse(l.hasPayableInstallment());
    }


}
