package com.samenea.banking.simia.service;

import com.samenea.banking.deposit.ChargeException;
import com.samenea.banking.deposit.IDeposit;
import com.samenea.banking.deposit.IDepositService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 9:52 AM
 */
@ContextConfiguration("classpath:context.xml")
public class DepositServiceIntegrationTest extends AbstractJUnit4SpringContextTests {
    public static final String DEBIT_BRANCH_CODE = "3000";
    @Autowired
    private IDepositService depositService;
    private final Integer amount = 10;
    private final String debit = "1113500101";
    private final String credit = "3152216737.58";
    private final String description = "soroosh";
    private final String docNumber = "10";
    private final String userId = "11111";
    private final String customerId = "681988851";


    @Test
    public void should_find_all_active_deposits_of_customer() {
        final List<IDeposit> deposits = depositService.findActiveDeposits(customerId);

        Assert.assertEquals(2, deposits.size());
    }

    @Test
    public void charge_should_increase_the_remained_amount_of_destination_deposit() throws ChargeException {
        Long beforeCharge = depositService.findDeposit(credit).getRemainedAmount();
        depositService.chargeDeposit(amount, debit, credit, description, userId, DEBIT_BRANCH_CODE);
        Long afterCharge = depositService.findDeposit(credit).getRemainedAmount();

        Assert.assertEquals(new Long(beforeCharge + amount), afterCharge);
    }

    @Test(expected = ChargeException.class)
    public void charge_should_throw_exception() throws ChargeException, IllegalAccessException {
        depositService.chargeDeposit(amount, "111111113500101", credit, description, userId, DEBIT_BRANCH_CODE);
    }

    @Test
    public void checkChargingFeasibility_should_not_change_remainedAmount() throws ChargeException {
        Long beforeCharge = depositService.findDeposit(credit).getRemainedAmount();
        depositService.checkChargingFeasibility(amount, debit, credit, description, userId, DEBIT_BRANCH_CODE);
        Long afterCharge = depositService.findDeposit(credit).getRemainedAmount();

        Assert.assertEquals(beforeCharge, afterCharge);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ChargeException_when_parameters_has_problem() throws ChargeException {
        depositService.checkChargingFeasibility(amount, debit, "3485215624.52", description, userId, DEBIT_BRANCH_CODE);

    }

}

