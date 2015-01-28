package com.samenea.banking.simia.model.repository;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/5/13
 * Time: 3:14 PM
 */
public interface SarResidInstallmentRepository {
    String findCreditAccountNumber(String loanNumber);

    String findDebitKarmozdAccount(String loanNumber);

    String findCreditKarmozdAccount(String loanNumber);

    String findInputResourceType(String loanNumber);
}
