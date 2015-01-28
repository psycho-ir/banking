package com.samenea.banking.simia.service.validator;//import org.slf4j.Logger;
//import com.samenea.commons.component.utils.log.LoggerFactory;

import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.service.loan.validator.InstallmentPaymentValidator;
import com.samenea.banking.validator.Violation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 3:00 PM
 */

//@Component
public class FakeValidator implements InstallmentPaymentValidator {

    @Override
    public List<Violation> validate(ILoan object) {
        List<Violation> violations = new ArrayList<Violation>();
        violations.add(new Violation() {
            @Override
            public String getCode() {
                return "0";
            }

            @Override
            public String getDescription() {
                return "description";
            }
        });
        return violations;
    }

}
