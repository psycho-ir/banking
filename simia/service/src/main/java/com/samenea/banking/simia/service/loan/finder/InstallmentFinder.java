package com.samenea.banking.simia.service.loan.finder;

import com.samenea.banking.simia.model.Installment;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.model.PayableInstallmentStatus;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/9/13
 * Time: 11:52 AM
 */
public interface InstallmentFinder {
    Installment findPayableInstallment(Loan loan, PayableInstallmentStatus payableInstallmentStatus);
}
