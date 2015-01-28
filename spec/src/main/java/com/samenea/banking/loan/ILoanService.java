package com.samenea.banking.loan;

import com.samenea.banking.IBankingService;
import com.samenea.banking.customer.ICustomer;

import java.util.List;

/**
 * Loan services that exposed for clients of core banking.
 *
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 10:22 PM
 */
public interface ILoanService extends IBankingService {
    /**
     * Finds a specific loan for given loanNumber.
     *
     * @param loanNumber
     * @return
     * @throws com.samenea.commons.component.model.exceptions.NotFoundException
     *                                  if loan does not exist in system
     * @throws IllegalArgumentException if loanNumber is Empty or null
     */
    ILoan findLoan(String loanNumber);

    /**
     * pays installment for a specific loan.
     *
     * @param userId
     * @param debitNumber
     * @param loanNumber
     * @param installmentNumber
     * @param amount
     * @return
     * @throws com.samenea.banking.NotSupportedException
     *
     * @throws IllegalArgumentException
     * @throws com.samenea.commons.component.model.exceptions.NotFoundException
     *
     */
    String payInstallment(String userId, String debitNumber, String loanNumber, String installmentNumber, Long amount);

    /**
     * Simulate the payInstallment but does not change the state of system.
     *
     * @param userId
     * @param debitNumber
     * @param loanNumber
     * @param installmentNumber
     * @param amount
     * @throws com.samenea.banking.NotSupportedException
     *
     * @throws IllegalArgumentException
     * @throws com.samenea.commons.component.model.exceptions.NotFoundException
     *
     */
    void checkPaymentFeasibility(String userId, String debitNumber, String loanNumber, String installmentNumber, Long amount);

    /**
     * Checks the state of loan and its installments.
     * Some of loans may not be valid for installment payment with our service.
     *
     * @param loanNumber
     * @return
     * @throws com.samenea.commons.component.model.exceptions.NotFoundException
     *
     */
    Boolean isValidForPayment(String loanNumber);

    ICustomer findCustomer(String loanNumber);
}
