package com.samenea.banking.simia.model;

import com.samenea.banking.deposit.IDeposit;
import org.springframework.util.Assert;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 4:35 PM
 */

public class Deposit implements IDeposit {
    private final String depositNumber;
    private final String description;
    private final Long remainedAmount;
    private final String customerCode;
    private final String branchCode;

    public Deposit(String depositNumber, String description, String customerCode, Long remainedAmount, String branchCode) {
        Assert.notNull(depositNumber,"depositNumber cannot be null.");
        Assert.hasText(depositNumber,"depositNumber cannot be empty.");
        Assert.notNull(customerCode,"customerCode cannot be null.");
        Assert.hasText(customerCode,"customerCode cannot be empty.");
        Assert.notNull(remainedAmount,"remainedAmount cannot be null.");
        Assert.hasText(branchCode,"branchCode cannot be empty.");
        Assert.notNull(branchCode,"branchCode cannot be null.");

        this.depositNumber = depositNumber;
        this.description = description;
        this.customerCode = customerCode;
        this.remainedAmount = remainedAmount;
        this.branchCode = branchCode;
    }


    public String getBranchCode() {
        return branchCode;
    }

    @Override
    public String getDepositNumber() {
        return depositNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getRemainedAmount() {
        return remainedAmount;
    }

    @Override
    public String getCustomerCode() {
        return customerCode;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositNumber='" + depositNumber + '\'' +
                ", description='" + description + '\'' +
                ", remainedAmount=" + remainedAmount +
                ", customerCode='" + customerCode + '\'' +
                '}';
    }
}
