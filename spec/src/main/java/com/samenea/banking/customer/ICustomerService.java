package com.samenea.banking.customer;

import com.samenea.banking.IBankingService;

import java.util.List;

/**
 * Customer services that exposed for clients of core banking.
 *
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 2:53 PM
 */

public interface ICustomerService extends IBankingService {
    ICustomer findCustomer(String customerCode, String username, String password);

    /**
     * Finds all of the customers that are owner of depositNumber
     *
     * @param depositNumber
     * @return
     */
    List<ICustomer> findCustomersOfDeposit(String depositNumber);
}
