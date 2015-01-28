package com.samenea.banking.simia.model.repository;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 4:26 PM
 */

public interface SequenceRepository {
    String getNewSequence(String branchCode,String date);
}
