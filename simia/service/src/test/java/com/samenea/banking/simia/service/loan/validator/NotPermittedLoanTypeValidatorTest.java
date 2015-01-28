package com.samenea.banking.simia.service.loan.validator;

import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.validator.Violation;
import com.samenea.commons.component.utils.log.LoggerFactory;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author: Soroosh Sarabadani
 * Date: 4/21/13
 * Time: 4:08 PM
 */

public class NotPermittedLoanTypeValidatorTest {
    private Logger logger = LoggerFactory.getLogger(NotPermittedLoanTypeValidatorTest.class);
    @InjectMocks
    private NotPermittedLoanTypeValidator validator;
    @Mock
    private List<String> permittedTypes;
    @Mock
    private Loan loan;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void validate_should_return_no_violation_when_permittedTypes_is_any() {
        when(permittedTypes.contains("any")).thenReturn(true);
        when(loan.getLoanType()).thenReturn("1");
        final List<Violation> violations = validator.validate(loan);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void validate_should_return_one_violation_when_permittedTypes_does_not_contain_loan_type() {
        when(permittedTypes.contains("1")).thenReturn(false);
        when(permittedTypes.contains("any")).thenReturn(false);
        when(loan.getLoanType()).thenReturn("1");
        final List<Violation> violations = validator.validate(loan);

        Assert.assertEquals(1, violations.size());
    }




}
