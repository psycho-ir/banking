package com.samenea.banking.simia.service.loan.finder;

import com.samenea.banking.simia.model.InstallmentStatus;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/9/13
 * Time: 11:53 AM
 */
public interface InstallmentFinderSelector {
    InstallmentFinder findInstallmentFinder(InstallmentStatus status);
}
