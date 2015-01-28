package com.samenea.banking.simia.repository.deposit;

import com.samenea.banking.simia.model.Deposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 5:30 PM
 */

public class DepositRowMapper implements RowMapper<Deposit> {
    private static final String DEPOSIT_NUMBER = "DPSTNO";
    private static final String DEPOSIT_DESCRIPTION = "DPSTDESC";
    private static final String CUSTOMER_ID = "OWNER_ID";
    private static final String REMAINED_AMOUNT = "REMAMNT";
    private static final String DEPOSIT_BRANCH_CODE = "DPSTBRANCHCODE";


    @Override
    public Deposit mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String depNumber = rs.getString(DEPOSIT_NUMBER);
        final String description = rs.getString(DEPOSIT_DESCRIPTION);
        final String customerId = rs.getString(CUSTOMER_ID);
        final Long remainedAmount = rs.getLong(REMAINED_AMOUNT);
        final String branchCode = rs.getString(DEPOSIT_BRANCH_CODE);

        return new Deposit(depNumber, description, customerId, remainedAmount, branchCode);
    }
}
