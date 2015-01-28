package com.samenea.banking.simia.service.loan.validator;


import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.AghdType;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.service.validator.ViolationCodes;
import com.samenea.banking.validator.AbstractViolation;
import com.samenea.banking.validator.Violation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/9/13
 * Time: 5:24 PM
 */

@Component
public class RemAmountValidator implements InstallmentPaymentValidator {
    private static final LoanPayedViolation LOAN_PAYED_VIOLATION = new LoanPayedViolation();

    @Override
    public List<Violation> validate(ILoan loan) {
        if (!(loan instanceof Loan)) {
            throw new IllegalArgumentException(String.format("loan type is:%s but must be %s.", loan.getClass(), Loan.class));
        }
        Loan l = (Loan) loan;
        List<Violation> violations = new ArrayList<Violation>();
        if (loan.getRemainedAmount() == 0) {
            if (l.getAghdType() == AghdType.JOALEH) {
                violations.add(new PaymentNotPossibleViolation());
            }
            violations.add(LOAN_PAYED_VIOLATION);
        }
        return violations;
    }

    private static class PaymentNotPossibleViolation extends AbstractViolation {
        @Override
        public String getCode() {
            return ViolationCodes.PAYMENT_NOT_POSSIBLE;
        }

        @Override
        public String getDescription() {
            return String.format("Payment for loan:%s is not possible.");
        }
    }

    private static class LoanPayedViolation implements Violation {
        @Override
        public String getCode() {
            return ViolationCodes.LOAN_PAYED;
        }

        @Override
        public String getDescription() {
            return String.format("loan:%s payed completely.");
        }
    }
}
