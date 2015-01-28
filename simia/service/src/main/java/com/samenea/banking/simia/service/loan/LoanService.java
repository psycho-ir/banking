package com.samenea.banking.simia.service.loan;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.ILoan;
import com.samenea.banking.loan.ILoanService;
import com.samenea.banking.simia.model.*;
import com.samenea.banking.simia.model.repository.LoanRepository;
import com.samenea.banking.simia.service.AbstractBankingService;
import com.samenea.banking.simia.service.loan.validator.InstallmentPaymentValidator;
import com.samenea.banking.simia.service.validator.ValidatorRunner;
import com.samenea.banking.simia.service.validator.ValidatorRunnerFactory;
import com.samenea.banking.validator.Violation;
import com.samenea.commons.component.utils.Environment;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 1:50 PM
 */
@Service
public class LoanService extends AbstractBankingService implements ILoanService {
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private Environment environment;
    @Value("${banking.loan.branchCode}")
    private String branchCode = "3000";
    /*    @Autowired
        private InstallmentFinderSelector installmentFinderSelector;*/

    @Autowired
    private ValidatorRunnerFactory validatorRunnerFactory;


    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER, readOnly = true)
    public ILoan findLoan(String loanNumber) {
        checkLoanNumberValidation(loanNumber);
        final Loan loan = (Loan) loanRepository.findLoan(loanNumber);
        return loan;
    }

    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER)
    public String payInstallment(String userId, String debitNumber, String loanNumber, String installmentNumber, Long amount) {
        final Boolean validForPayment = isValidForPayment(loanNumber);
        if (!validForPayment) {
            throw new IllegalStateException(String.format("Loan %s is not valid for payment", loanNumber));
        }
        final Loan loan = (Loan) findLoan(loanNumber);
        if (!loan.getPayableInstallment().getInstallmentNumber().equals(installmentNumber)) {
            throw new IllegalArgumentException("Installment Number is not compatible.");
        }
        checkAmount(amount, loan.getPayableInstallment());
        String currentDate = SimiaUtils.getCurrentDate(environment.getCurrentDate());
        final String seqence = loanRepository.payInstallment(loanNumber, Long.valueOf(debitNumber), currentDate, userId, "99", branchCode);

        return seqence;
    }


    @Override
    @Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER, propagation = Propagation.REQUIRES_NEW)
    public void checkPaymentFeasibility(String userId, String debitNumber, String loanNumber, String
            installmentNumber, Long amount) {
        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();

        payInstallment(userId, debitNumber, loanNumber, installmentNumber, amount);

    }

    @Override
    public Boolean isValidForPayment(String loanNumber) {

        checkLoanNumberValidation(loanNumber);
        final ILoan loan = findLoan(loanNumber);
        if (!loan.hasPayableInstallment()) {
            return false;
        }
        final IInstallment payableInstallment = loan.getPayableInstallment();

        final ValidatorRunner validatorRunner = validatorRunnerFactory.createValidatorRunner(InstallmentPaymentValidator.class, loan);
        final List<Violation> violations = validatorRunner.run();
        logger.info("These violations occured: {}", violations.toString());
        return violations.size() == 0 ? true : false;
    }

    @Override
    public ICustomer findCustomer(String loanNumber) {
        checkLoanNumberValidation(loanNumber);
        return loanRepository.findCustomer(loanNumber);
    }

/*    private boolean isInstallmentAfterToday(IInstallment payableInstallment) {
        return environment.getCurrentDate().before(payableInstallment.getInstallmentDate());
    }*/

    private void checkAmount(Long amount, IInstallment installment) {
        if (!installment.getPayableAmount().equals(amount)) {
            throw new IllegalArgumentException(String.format("amount:%s is not equal with installment payable amount:%s", amount, installment.getPayableAmount()));
        }
    }

    private void checkLoanNumberValidation(String loanNumber) {
        Assert.notNull(loanNumber, "loanNumber cannot be null.");
        Assert.hasText(loanNumber, "loanNumber cannot be empty.");
    }


}