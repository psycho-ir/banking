package com.samenea.banking.simia.service;

import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.simia.model.Installment;
import com.samenea.banking.simia.model.repository.LoanRepository;
import com.samenea.banking.simia.service.loan.LoanService;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import com.samenea.commons.component.utils.Environment;
import com.samenea.banking.simia.model.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 9:52 AM
 */

public class LoanServiceTest {

    @InjectMocks
    @Spy
    private LoanService loanService;
    @Mock
    private Loan loan;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private Environment environment;

    private final String loanNumber = "111";
    private final Date date = Calendar.getInstance().getTime();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(environment.getCurrentDate()).thenReturn(date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findLoan_should_throw_exception_if_loanNumber_is_empty() {
        loanService.findLoan("");

    }

    @Test(expected = IllegalArgumentException.class)
    public void findLoan_should_throw_exception_if_loanNumber_is_null() {
        loanService.findCustomer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findCustomer_should_throw_exception_if_loanNumber_is_empty() {
        loanService.findCustomer("");

    }

    @Test(expected = IllegalArgumentException.class)
    public void findCustomer_should_throw_exception_if_loanNumber_is_null() {
        loanService.findLoan(null);
    }

    @Test
    public void findLoan_should_call_findLoan_of_repository() {
        Installment installment = new Installment("1", Calendar.getInstance().getTime(), 1000L, 1000L, 0l);
        when(loanRepository.findLoan(loanNumber)).thenThrow(NotFoundException.class);
        List installments = new ArrayList<IInstallment>();
        installments.add(installment);
//        when(loanRepository.findInstallments(loanNumber)).thenReturn(installments);
        try {
            loanService.findLoan(loanNumber);
        } catch (NotFoundException e) {
        } finally {
            verify(loanRepository).findLoan(loanNumber);
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void isValidPayment_should_throw_exception_if_loanNumber_is_empty() {
        loanService.isValidForPayment("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void isValidPayment_should_throw_exception_if_loanNumber_is_null() {
        loanService.isValidForPayment(null);
    }

/*    @Test
    public void isValidPayment_should_return_false_when_payableInstallment_date_is_greater_that_now() {
        ILoan loan = mock(ILoan.class);
        IInstallment installment = mock(IInstallment.class);
        final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);
        when(installment.getInstallmentDate()).thenReturn(instance.getTime());
        when(loan.getPayableInstallment()).thenReturn(installment);
        when(loan.hasPayableInstallment()).thenReturn(true);
        Mockito.doReturn(loan).when(loanService).findLoan(loanNumber);

        final Boolean validForPayment = loanService.isValidForPayment(loanNumber);
        Assert.assertFalse(validForPayment);
    }*/

}
