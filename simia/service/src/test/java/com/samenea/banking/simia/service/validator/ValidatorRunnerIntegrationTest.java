package com.samenea.banking.simia.service.validator;


import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.AghdType;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.service.loan.validator.InstallmentPaymentValidator;
import com.samenea.banking.validator.Violation;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/6/13
 * Time: 2:24 PM
 */
@ContextConfiguration({"classpath*:contexts/validator-context.xml"})
public class ValidatorRunnerIntegrationTest extends AbstractJUnit4SpringContextTests {

    public static final int VIOLATION_COUNT = 1;
    @Autowired
    private ValidatorRunnerFactory validatorRunnerFactory;
    private String loanDescription = "sss";


    @Test
    @Ignore
    public void should_call_all_validators() {
/*        ILoan loan = new Loan("", null, "w", 99, 111, "11", loanDescription, AghdType.EJARE_BE_SHARTE_TAMLIK);
        final ValidatorRunner validatorRunner = validatorRunnerFactory.createValidatorRunner(InstallmentPaymentValidator.class, loan);
        final List<Violation> violations = validatorRunner.run();
        Assert.assertEquals(VIOLATION_COUNT, violations.size());
        Assert.assertEquals("0", violations.get(0).getCode());
        Assert.assertEquals("description", violations.get(0).getDescription());*/
    }


}
