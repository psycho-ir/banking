package com.samenea.banking.simia.service.loan.validator;

import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.model.AghdType;
import com.samenea.banking.simia.service.validator.ViolationCodes;
import com.samenea.banking.validator.AbstractViolation;
import com.samenea.banking.validator.Violation;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/9/13
 * Time: 5:41 PM
 */
@Component
public class NotPermittedAghdTypeValidator implements InstallmentPaymentValidator {
    private Logger logger = LoggerFactory.getLogger(NotPermittedAghdTypeValidator.class);
    private static final SalafViolation SALAF_VIOLATION = new SalafViolation();
    private static final KharidDeinViolation KHARID_DEIN_VIOLATION = new KharidDeinViolation();

    @Override
    public List<Violation> validate(ILoan loan) {
        if (!(loan instanceof Loan)) {
            throw new IllegalArgumentException(String.format("loan type is:%s but must be %s.", loan.getClass(), Loan.class));
        }
        Loan l = (Loan) loan;

        final List<Violation> violations = new ArrayList<Violation>();
        if (l.getAghdType() == AghdType.KHARIDE_DEIN) {
            violations.add(KHARID_DEIN_VIOLATION);
        }
        if (l.getAghdType() == AghdType.SALAF) {
            violations.add(SALAF_VIOLATION);
        }
        return violations;
    }

    private static class KharidDeinViolation extends AbstractViolation {

        @Override
        public String getCode() {
            return ViolationCodes.KHARID_DEIN_CANNOT_PAY;
        }

        @Override
        public String getDescription() {
            return String.format("Payment for loan is not possible. Because it's type is KHARID_DEIN.");
        }
    }

    private static class SalafViolation implements Violation {

        @Override
        public String getCode() {
            return ViolationCodes.SALAF_CANNOT_PAY;
        }

        @Override
        public String getDescription() {
            return String.format("Payment for loan is not possible. Because it's type is SALAF.");
        }
    }
}
