package com.samenea.banking.simia.service;

import com.samenea.banking.deposit.ChargeException;
import com.samenea.banking.simia.model.Deposit;
import com.samenea.banking.simia.model.repository.DepositRepository;
import com.samenea.banking.simia.model.repository.SequenceRepository;
import com.samenea.commons.component.utils.Environment;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/17/13
 * Time: 3:10 PM
 */

public class DepositServiceTest {

    public static final String DEBIT_BRANCH_CODE = "3000";
    @InjectMocks
    @Spy
    private com.samenea.banking.simia.service.deposit.DepositService depositService;
    @Mock
    private DepositRepository depositRepository;
    @Mock
    private Environment environment;
    @Mock
    private SequenceRepository sequenceRepository;
    @Mock
    private DocumentGenerator documentGenerator = new DefaultDocumentGenerator();

    private static final Date date = Calendar.getInstance().getTime();
    private final Integer amount = 1000;
    private final String debit = "s-100";
    private final String credit = "d-100";
    private final String description = "DESC";
    private final String docnumber = "DOC-100";
    private final String userId = "U";


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(environment.getCurrentDate()).thenReturn(date);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("PRM_SUCCESS", "1");
        when(depositRepository.insertRowOfCharge(anyString(), anyString(), anyInt(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(parameters);
    }


    @Test(expected = IllegalArgumentException.class)
    public void charge_should_throw_exception_when_depositNumber_is_not_valid_for_charging() throws ChargeException {
        final String timestamp = String.valueOf(date.getTime());
        when(depositService.isValidForCharging(credit)).thenReturn(false);
        when(documentGenerator.getDocNumber()).thenReturn(timestamp);
        depositService.chargeDeposit(amount, debit, credit, description, userId,DEBIT_BRANCH_CODE);

    }

    @Test
    public void charge_should_call_respository_when_validation_returns_true() throws ChargeException {
        when(depositRepository.findDeposit(credit)).thenReturn(new Deposit(credit, "sdawsas", "11", 11111L, "300"));
        final String timestamp = String.valueOf(date.getTime());
        when(depositService.isValidForCharging(credit)).thenReturn(true);
        when(documentGenerator.getDocNumber()).thenReturn(timestamp);
        depositService.chargeDeposit(amount, debit, credit, description, userId, DEBIT_BRANCH_CODE);

        verify(depositRepository).insertRowOfCharge(eq(credit), eq("0"), eq(amount), eq(description), eq(timestamp), any(String.class), eq(userId), any(String.class), eq("3000"), eq(DEBIT_BRANCH_CODE));
        verify(depositRepository).insertRowOfCharge(eq("0"), eq(debit), eq(-amount), eq(description), eq(timestamp), any(String.class), eq(userId), any(String.class), eq("3000"), eq(DEBIT_BRANCH_CODE));
    }

    @Test
    public void charge_should_return_the_sequence_number_of_transaction() throws ChargeException {
        when(depositRepository.findDeposit(credit)).thenReturn(new Deposit(credit, "sdawsas", "11", 11111L, "300"));
        final String timestamp = String.valueOf(date.getTime());
        when(sequenceRepository.getNewSequence(anyString(), anyString())).thenReturn("1111");
        when(depositService.isValidForCharging(credit)).thenReturn(true);
        when(documentGenerator.getDocNumber()).thenReturn(timestamp);
        final String result = depositService.chargeDeposit(amount, debit, credit, description, userId, DEBIT_BRANCH_CODE);

        Assert.assertEquals("1111", result);
    }

    @Test
    public void isValidForCharging_should_return_repository_result() {
        when(depositRepository.isDepositValidForCharging(credit)).thenReturn(false);
        final Boolean validForCharging = depositService.isValidForCharging(credit);
        Assert.assertFalse(validForCharging);
    }


}
