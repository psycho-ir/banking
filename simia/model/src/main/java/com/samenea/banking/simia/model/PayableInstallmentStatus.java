package com.samenea.banking.simia.model;//import org.slf4j.Logger;
//import com.samenea.commons.component.utils.log.LoggerFactory;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/5/13
 * Time: 6:47 PM
 */

public class PayableInstallmentStatus {

    public PayableInstallmentStatus(InstallmentStatus status, Integer installmentNumber) {
        this.status = status;
        this.installmentNumber = installmentNumber;
    }

    public InstallmentStatus getStatus() {
        return status;
    }

    private InstallmentStatus status;
    private Integer installmentNumber;

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }
}
