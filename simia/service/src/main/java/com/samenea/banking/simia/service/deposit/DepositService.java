package com.samenea.banking.simia.service.deposit;

import com.samenea.banking.deposit.ChargeException;
import com.samenea.banking.deposit.IDeposit;
import com.samenea.banking.deposit.IDepositService;
import com.samenea.banking.simia.model.Deposit;
import com.samenea.banking.simia.model.SimiaMessageHandler;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.banking.simia.model.repository.DepositRepository;
import com.samenea.banking.simia.model.repository.SequenceRepository;
import com.samenea.banking.simia.service.AbstractBankingService;
import com.samenea.banking.simia.service.DocumentGenerator;
import com.samenea.commons.component.utils.Environment;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/17/13
 * Time: 2:27 PM
 */
@Service
public class DepositService extends AbstractBankingService implements IDepositService {
    private static final Logger logger = LoggerFactory.getLogger(DepositService.class);

    @Autowired
    private DepositRepository depositRepository;
    @Autowired
    private SequenceRepository sequenceRepository;
    @Autowired
    private Environment environment;
    @Autowired
    private DocumentGenerator documentGenerator;
    @Value("${banking.deposit.branchCode}")
    private String branchCode = "3000";

    @Override
    public List<IDeposit> findActiveDeposits(String customerId) {
        return (List<IDeposit>) depositRepository.findActiveDeposits(customerId);
    }

    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER, rollbackFor = Throwable.class)
    public String chargeDeposit(Integer amount, String debitNumber, String creditNumber, String description, String userId, String debitBranchCode) throws IllegalStateException, ChargeException {
        validateCharging(creditNumber);
        final Deposit deposit = (Deposit) findDeposit(creditNumber);
        logger.debug("About charging deposit, from:{} to:{} amount:{}", debitNumber, creditNumber, amount);
        final String currentDate = SimiaUtils.getCurrentDate(environment.getCurrentDate());
        final String sequenceNumber = sequenceRepository.getNewSequence(branchCode, currentDate);

        Map<String, Object> results = depositRepository.insertRowOfCharge("0", debitNumber, -amount, description, documentGenerator.getDocNumber(), sequenceNumber, userId, currentDate, branchCode, debitBranchCode);
        new SimiaMessageHandler(results, ChargeException.class).handle();
        Map<String, Object> results2 = depositRepository.insertRowOfCharge(creditNumber, "0", amount, description, documentGenerator.getDocNumber(), sequenceNumber, userId, currentDate, branchCode, debitBranchCode);
        new SimiaMessageHandler(results2, ChargeException.class).handle();
        return sequenceNumber;
    }

    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER)
    public IDeposit findDeposit(String depositNumber) {
        return depositRepository.findDeposit(depositNumber);
    }

    @Override
    public Boolean isValidForCharging(String depositNumber) {
        return depositRepository.isDepositValidForCharging(depositNumber);
    }

    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER, propagation = Propagation.REQUIRES_NEW)
    public void checkChargingFeasibility(Integer amount, String debitNumber, String creditNumber, String description, String userId, String debitBranchCode) throws ChargeException, IllegalStateException {
        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        chargeDeposit(amount, debitNumber, creditNumber, description, userId,debitBranchCode);
    }

    private void validateCharging(String creditNumber) {
        final Boolean validForCharging = isValidForCharging(creditNumber);
        if (!validForCharging) {
            throw new IllegalArgumentException("creditNumber is not valid for charging");
        }
    }


}
