package com.samenea.banking.simia.service;

import com.samenea.banking.IBankingService;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/20/13
 * Time: 10:42 AM
 */

public class AbstractBankingService implements IBankingService {
    @Override
    public final String getVendorName() {
        return "simia";
    }
}
