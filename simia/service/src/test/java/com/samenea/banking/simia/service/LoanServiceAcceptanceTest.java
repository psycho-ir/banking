package com.samenea.banking.simia.service;

import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.banking.simia.service.loan.LoanService;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/12/13
 * Time: 1:32 PM
 */

@ContextConfiguration("classpath*:context.xml")
@TransactionConfiguration(transactionManager = SimiaUtils.SIMIA_TRANSACTION_MANAGER)
@Transactional
public class LoanServiceAcceptanceTest extends AbstractTransactionalJUnit4SpringContextTests {
    public static final String WITH_PENALTY_PARTAILLY_PAID_LOAN_NUMBER = "0084901600091";
    public static final String SAR_RESID_PARTIALLY_PAID_LOAN_NUMBER = "0440901600398";
    private final String userId = "11111";
    private final String debitNumber = "1110100201";
    public static final String PAYED_LOAN_NUMBER = "0100901600445";
    public static final String JOALEH_WITH_ZERO_REMAINED_LOAN_NUMBER = "0100901600445";
    public static final String DEIN_LOAN_NUMBER = "0050912802940";
    public static final String FUTURE_LOAN_NUMBER = "0005901601585";
    public static final String MOAVAGH_WITH_FOLLOWUP_LOAN_NUMBER = "0084901500548";
    public static final String MOAVAGH_WITHOUT_FOLLOWUP_LOAN_NUMBER = "0084901600088";

    @Autowired
    private LoanService loanService;

    @Test
    public void isValid_should_return_false_when_loan_is_payed_completely() {
        final Boolean validForPayment = loanService.isValidForPayment(PAYED_LOAN_NUMBER);
        Assert.assertFalse(validForPayment);

    }

    @Test
    public void isValid_should_return_false_when_loan_is_joaleh_with_zero_remained() {
        final Boolean validForPayment = loanService.isValidForPayment(JOALEH_WITH_ZERO_REMAINED_LOAN_NUMBER);
        Assert.assertFalse(validForPayment);
    }

    @Test
    public void isValid_should_return_false_when_loan_is_DEIN() {
        final Boolean validForPayment = loanService.isValidForPayment(DEIN_LOAN_NUMBER);
        Assert.assertFalse(validForPayment);
    }

/*    @Test
    public void isValid_should_return_false_when_loan_is_MOAVAGH_WITH_FOLLOWUP() {
        final Boolean validForPayment = loanService.isValidForPayment(MOAVAGH_WITH_FOLLOWUP_LOAN_NUMBER);
        Assert.assertFalse(validForPayment);
    }*/

/*    @Test
    public void isValid_should_return_false_when_installment_is_in_future() {
        final Boolean validForPayment = loanService.isValidForPayment(FUTURE_LOAN_NUMBER);
        Assert.assertFalse(validForPayment);
    }*/

    @Test
    public void isValid_should_return_true_when_installment_is_MOAVAGH_WITHOUT_FOLLOWUP() {
        final Boolean validForPayment = loanService.isValidForPayment(MOAVAGH_WITHOUT_FOLLOWUP_LOAN_NUMBER);
        Assert.assertTrue(validForPayment);
    }

    @Test
    @Ignore
    public void pay_should_works_when_loan_is_MOAVAGH_WITHOUT_FOLLOWUP() {
        final ILoan beforeLoan = loanService.findLoan(MOAVAGH_WITHOUT_FOLLOWUP_LOAN_NUMBER);
        final Long payableAmount = beforeLoan.getPayableInstallment().getPayableAmount();
        loanService.payInstallment(userId, debitNumber, beforeLoan.getLoanNumber(), beforeLoan.getPayableInstallment().getInstallmentNumber(), payableAmount);
        final ILoan afterLoan = loanService.findLoan(MOAVAGH_WITHOUT_FOLLOWUP_LOAN_NUMBER);
        Assert.assertTrue(beforeLoan.getRemainedAmount() > afterLoan.getRemainedAmount());
    }

    @Test
    public void pay_should_works_when_loan_has_penalty_and_partially_paid() {

        final ILoan beforeLoan = loanService.findLoan(WITH_PENALTY_PARTAILLY_PAID_LOAN_NUMBER);
        final Long payableAmount = beforeLoan.getPayableInstallment().getPayableAmount();
        loanService.payInstallment(userId, debitNumber, beforeLoan.getLoanNumber(), beforeLoan.getPayableInstallment().getInstallmentNumber(), payableAmount);
        final ILoan afterLoan = loanService.findLoan(WITH_PENALTY_PARTAILLY_PAID_LOAN_NUMBER);
        Assert.assertTrue(beforeLoan.getRemainedAmount() > afterLoan.getRemainedAmount());
    }

    @Test
    public void pay_should_works_when_loan_is_sar_resid_and_partially_paid() {
        System.out.println(TransactionInterceptor.currentTransactionStatus());
        final ILoan beforeLoan = loanService.findLoan(SAR_RESID_PARTIALLY_PAID_LOAN_NUMBER);
        final Long payableAmount = beforeLoan.getPayableInstallment().getPayableAmount();
        loanService.payInstallment(userId, debitNumber, beforeLoan.getLoanNumber(), beforeLoan.getPayableInstallment().getInstallmentNumber(), payableAmount);
        final ILoan afterLoan = loanService.findLoan(SAR_RESID_PARTIALLY_PAID_LOAN_NUMBER);
        Assert.assertTrue(beforeLoan.getRemainedAmount() > afterLoan.getRemainedAmount());
    }



}
