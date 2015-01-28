package com.samenea.banking;

/**
 * Common methods for all Banking Services come here.
 */
public interface IBankingService {
    /**
     * Client may need the name of vendor for some customization. this method returns vendor name.
     *
     * @return the name of vendor (e.g. simia , bfub , ...)
     */
    String getVendorName();
}
