package com.samenea.banking.loan;

import java.util.Date;

/**
 * An abstract base model for installments of core bankings.
 * clients use this model for accessing to installment objects of core banking.
 *
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 10:22 PM
 */
public interface IInstallment {
    String getInstallmentNumber();

    Long getAmount();

    Long getPenaltyAmount();

    Long getUnPayedAmount();

    public Date getInstallmentDate();

    public Long getPayableAmount();
}
