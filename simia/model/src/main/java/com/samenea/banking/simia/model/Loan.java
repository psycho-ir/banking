package com.samenea.banking.simia.model;

import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.ILoan;

import java.util.Date;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 1:26 PM
 */

public class Loan implements ILoan {
    private final String loanNumber;
    private final Date getIssueDate;
    private final String rate;
    private final Long originalAmount;
    private final Long remainedAmount;
    private final String loanType;
    private final String loanDescription;
    private String payableInstallmenNumber;
    private IInstallment payableInstallment;
    private final AghdType aghdType;

    public Loan(String loanNumber, Date issueDate, String rate, Long originalAmount, Long remainedAmount, String loanType, String loanDescription, AghdType aghdType) {
        this.loanNumber = loanNumber;
        this.getIssueDate = issueDate;
        this.rate = rate;
        this.originalAmount = originalAmount;
        this.remainedAmount = remainedAmount;
        this.aghdType = aghdType;
        this.loanType = loanType;
        this.loanDescription = loanDescription;
    }

    @Override
    public String getLoanNumber() {
        return this.loanNumber;
    }

    @Override
    public Date getIssueDate() {
        return this.getIssueDate;
    }

    @Override
    public String getRate() {
        return this.rate;
    }


    @Override
    public Long getOriginalAmount() {
        return this.originalAmount;
    }


    @Override
    public Long getRemainedAmount() {
        return this.remainedAmount;
    }

    public AghdType getAghdType() {
        return this.aghdType;
    }

    @Override
    public IInstallment getPayableInstallment() {
        if (!hasPayableInstallment()) {
            throw new IllegalStateException("payable installment does not exist.");
        }
        return this.payableInstallment;

    }

    @Override
    public Boolean hasPayableInstallment() {
        return this.payableInstallment != null;
    }

    public void setPayableInstallment(IInstallment installment) {

        this.payableInstallment = installment;
    }

    public String getPayableInstallmenNumber() {
        return payableInstallmenNumber;
    }

    public void setPayableInstallmenNumber(String payableInstallmenNumber) {
        this.payableInstallmenNumber = payableInstallmenNumber;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public String getLoanType() {
        return loanType;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber='" + loanNumber + '\'' +
                ", getIssueDate=" + getIssueDate +
                ", rate='" + rate + '\'' +
                ", originalAmount=" + originalAmount +
                ", remainedAmount=" + remainedAmount +
                ", aghdType='" + aghdType + '\'' +
                ", payableInstallmenNumber='" + payableInstallmenNumber + '\'' +
                ", payableInstallment=" + payableInstallment +
                ", aghdType=" + aghdType +
                '}';
    }
}
