package com.samenea.banking.deposit;

/**
 * An abstract base model for deposits of core bankings.
 * clients use this model for accessing to deposit objects of core banking.
 */
public interface IDeposit {
    /**
     * The number of deposit of the customer
     */
    String getDepositNumber();

    /**
     * Description of the deposit
     */
    String getDescription();

    /**
     * Remained amount of deposit
     */

    Long getRemainedAmount();

    /**
     * OwnerCode of the deposit
     */
    String getCustomerCode();
}
