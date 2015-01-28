package com.samenea.banking.simia.service.loan.payment;

import com.samenea.banking.simia.model.InstallmentStatus;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 4:13 PM
 */
public interface InstallmentPaymentSelector {
    InstallmentPayment findInstallmentPayment(InstallmentStatus status);
}
