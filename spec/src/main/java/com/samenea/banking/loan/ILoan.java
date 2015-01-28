package com.samenea.banking.loan;

import java.util.Date;

/**
 * An abstract base model for loans of core bankings.
 * clients use this model for accessing to loan objects of core banking.
 *
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 10:22 PM
 */
public interface ILoan {
    String getLoanNumber();


    Date getIssueDate();

    String getRate();

    Long getOriginalAmount();

    Long getRemainedAmount();

    String getLoanDescription();

    IInstallment getPayableInstallment();

    Boolean hasPayableInstallment();

}
