package com.samenea.banking.deposit;

import com.samenea.banking.IBankingService;

import java.util.List;

/**
 * Deposit services that exposed for clients of core banking.
 *
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 2:53 PM
 */
public interface IDepositService extends IBankingService {

    /**
     * Finds the active deposits of customer.
     * active deposits are the deposits that client can have transaction on them.
     *
     * @param customerId is the owner of deposits.
     * @return all the active accounts.
     */
    List<IDeposit> findActiveDeposits(String customerId);

    /**
     * Charges the deposit of specific customer.
     * This method must use isValidForCharging for validation of deposit number.
     *
     * @param amount
     * @param creditNumber the depositNumber of the deposit that will charge after this method.
     * @param debitNumber  the deposit that will be debit
     * @param description  description about the charge transaction.
     * @param userId       code of user who charge account.
     * @return a unique number that is the transactionId in core banking.
     * @throws #ChargeException          when charge cannot be occurred.
     * @throws #IllegalArgumentException when some of arguments is not valid.
     */
    String chargeDeposit(Integer amount, String debitNumber, String creditNumber, String description, String userId, String debitBranchCode) throws ChargeException, IllegalArgumentException;

    /**
     * @param depositNumber
     * @return
     */
    IDeposit findDeposit(String depositNumber);

    /**
     * Specifies which deposit numbers can charge.
     * Some of deposits as Long Term deposit cannot charge.
     *
     * @param depositNumber The deposit number that we want to know its validation.
     * @return true when depsot is valid for charging
     */
    Boolean isValidForCharging(String depositNumber);

    /**
     * Some times we need to simulate charging.
     * this method must rollback transaction even if everything were ok.
     * after calling this method if any exception was thrown, it's probably possible to charging deposit.
     *
     * @param amount
     * @param creditNumber the depositNumber of the deposit that will charge after this method.
     * @param debitNumber  the deposit that will be debit
     * @param description  description about the charge transaction.
     * @param userId       code of user who charge account.
     * @throws ChargeException          when a problem occured in charging,
     *                                  error number will throw with this exception.
     * @throws IllegalArgumentException
     */
    void checkChargingFeasibility(Integer amount, String debitNumber, String creditNumber, String description, String userId, String debitBranchCode) throws ChargeException, IllegalArgumentException;

}
