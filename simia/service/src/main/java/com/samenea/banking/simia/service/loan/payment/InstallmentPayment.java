package com.samenea.banking.simia.service.loan.payment;

import com.samenea.banking.simia.model.*;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 3:55 PM
 */
public interface InstallmentPayment {
    String pay(Loan loan, String debitNumber, String userId, String branchCode);
}
