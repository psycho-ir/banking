package com.samenea.banking.simia.model.repository;

import com.samenea.banking.deposit.IDeposit;
import com.samenea.banking.simia.model.Deposit;

import java.util.List;
import java.util.Map;

public interface DepositRepository {
    List<? extends IDeposit> findActiveDeposits(String customerId);

    Map<String, Object> insertRowOfCharge(String accno, String acccode, Integer amount, String description,
                                          String docno, String seqno, String userId, String docDate, String branchCode, String creditBranchCode);

    Deposit findDeposit(String depositNumber);

    Boolean isDepositValidForCharging(String depositNumber);
}
