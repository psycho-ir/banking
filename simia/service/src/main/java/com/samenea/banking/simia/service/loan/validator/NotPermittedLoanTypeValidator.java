package com.samenea.banking.simia.service.loan.validator;

import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.service.validator.ViolationCodes;
import com.samenea.banking.validator.Violation;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 4/21/13
 * Time: 3:31 PM
 */

@Component
public class NotPermittedLoanTypeValidator implements InstallmentPaymentValidator {
    public static final String ANY = "any";
    private Logger logger = LoggerFactory.getLogger(NotPermittedLoanTypeValidator.class);
    @Value("${banking.loan.permittedTypes}")
    private String permittedTypesString = "";
    private List<String> permittedTypes = new ArrayList<String>();

    @PostConstruct
    public void init() {
        logger.info("NotPermittedLoanTypeValidator is constructed.");
        createListFromTypes(permittedTypesString, permittedTypes);
    }

    @Override
    public List<Violation> validate(ILoan loan) {
        List<Violation> violations = new ArrayList<Violation>();
        if (!(loan instanceof Loan)) {
            throw new IllegalArgumentException(String.format("loan type is:%s but must be %s.", loan.getClass(), Loan.class));
        }
        Loan l = (Loan) loan;
        if (permittedTypes.contains(ANY)) {
            return violations;
        }

        if (!permittedTypes.contains(l.getLoanType())) {
            violations.add(new NotPermittedLoanTypeViolation(l.getLoanType()));
        }

        return violations;
    }


    private void createListFromTypes(String typeString, List<String> typeList) {
        final String[] types = typeString.split(",");
        for (String type : types) {
            typeList.add(type.trim().toLowerCase());
        }
    }

    private static class NotPermittedLoanTypeViolation implements Violation {

        private final String code;

        public NotPermittedLoanTypeViolation(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return ViolationCodes.LOAN_TYPE_IS_NOT_VALID;
        }

        @Override
        public String getDescription() {
            return String.format("Loan type: %s is not permitted for payment.", this.code);
        }
    }

}


