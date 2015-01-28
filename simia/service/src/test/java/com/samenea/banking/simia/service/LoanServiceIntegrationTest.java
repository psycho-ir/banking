package com.samenea.banking.simia.service;

import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.ILoan;
import com.samenea.banking.loan.ILoanService;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 10:09 AM
 */


//@Ignore // currently there is no data for this test todo enable it later
@ContextConfiguration("classpath:context.xml")
@TransactionConfiguration(transactionManager = SimiaUtils.SIMIA_TRANSACTION_MANAGER)
public class LoanServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    public static final String LOAN_NUMBER = "0300911505147";
    @Autowired
    private ILoanService loanService;
    private final String userId = "11111";
    private final String debitNumber = "1110100201";
    private final String loanNumber = "0084901600046";

    @Test(expected = NotFoundException.class)
    public void findLoan_should_return_return_exception() {
        loanService.findLoan("0000");
    }

    @Test
    public void findLoan_should_return_loan() {
        final ILoan loan = loanService.findLoan(LOAN_NUMBER);
        Assert.assertNotNull(loan);
    }

    @Test
    public void findLoan_should_set_payableInstallment_of_loan() {
        final ILoan loan = loanService.findLoan("0415901600066");
        Assert.assertNotNull(loan.getPayableInstallment());
    }




    @Ignore
    @Test
    public void hasPayableInstallment_of_loan_should_return_false() {
        final ILoan loan = loanService.findLoan("0084901600046");
        Assert.assertFalse(loan.hasPayableInstallment());
    }

    @Test
    @Ignore
    @Rollback(true)
    public void payInstallment_should_pay() {
        final ILoan loan = loanService.findLoan("0084901600046");
        loanService.payInstallment(userId, debitNumber, loanNumber, loan.getPayableInstallment().getInstallmentNumber(), loan.getPayableInstallment().getPayableAmount());
        final ILoan afterLoan = loanService.findLoan("0084901600046");
        Assert.assertFalse(loan.getRemainedAmount().equals(afterLoan.getRemainedAmount()));
    }

    @Test
    public void isValid_should_return_false_when_rem_amount_is_0() {
        final Boolean validForPayment = loanService.isValidForPayment("0100901600773");
        Assert.assertFalse(validForPayment);
    }

    @Ignore
    @Test
    @Rollback(true)
    public void payInstallment_should_change_remain_amount_of_loan() {
        final String loanNumber1 = "0093901600628";
        final ILoan loan = loanService.findLoan(loanNumber1);
        loanService.payInstallment(userId, debitNumber, loanNumber1, "14", loan.getPayableInstallment().getPayableAmount());
        final ILoan loan2 = loanService.findLoan(loanNumber1);
        Assert.assertTrue(loan2.getRemainedAmount() < loan.getRemainedAmount());
    }

    @Test
    @Rollback(true)
    @Ignore
    public void payInstallment_should_pay_moavagh_installments() {
        final ILoan loan = loanService.findLoan("0300901602209");
        loanService.payInstallment(userId, debitNumber, "0300901602209", loan.getPayableInstallment().getInstallmentNumber(), loan.getPayableInstallment().getPayableAmount());
        final ILoan afterLoan = loanService.findLoan("0300901602209");
        Assert.assertFalse(loan.getRemainedAmount().equals(afterLoan.getRemainedAmount()));
    }

    @Test(expected = NotFoundException.class)
    public void findCustomer_should_throw_exception_when_loan_does_not_exist() {
        loanService.findCustomer("1");
    }

    @Test
    public void findCustomer_should_find_customer_when_loan_exists() {
        loanService.findCustomer(loanNumber);
    }


}
