package com.samenea.banking.simia.model;

import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.InstallmentStatus;

import java.util.Date;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 1:27 PM
 */

public class Installment implements IInstallment {

    private final String installmentNumber;
    private final Date installmentDate;
    private final Long amount;
    private Long penaltyAmount;
    private final Long payedAmount;

    public Installment(String installmentNumber, Date installmentDate, Long amount, Long penaltyAmount, Long payedAmount) {
        this.installmentNumber = installmentNumber;
        this.installmentDate = installmentDate;
        this.amount = amount;
        this.penaltyAmount = penaltyAmount;
        this.payedAmount = payedAmount;

    }

    @Override
    public String getInstallmentNumber() {

        return this.installmentNumber;
    }

    @Override
    public Long getAmount() {
        return this.amount;
    }

    @Override
    public Long getPenaltyAmount() {
        return this.penaltyAmount;
    }

    @Override
    public Long getUnPayedAmount() {
        return this.amount - this.payedAmount;
    }

    @Override
    public Long getPayableAmount() {
        return getPenaltyAmount() + getUnPayedAmount();

    }

    public void setPenaltyAmount(Long penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    @Override
    public Date getInstallmentDate() {
        return installmentDate;
    }
}
