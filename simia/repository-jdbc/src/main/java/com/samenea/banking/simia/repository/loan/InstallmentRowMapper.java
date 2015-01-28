package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.loan.InstallmentStatus;
import com.samenea.banking.simia.model.Installment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 2:38 PM
 */

public class InstallmentRowMapper implements RowMapper<Installment> {
    private static final String INSTALLMENT_NUMBER = "GHST_NO";
    private static final String INSTALLMENT_DATE = "GHST_DATE";
    private static final String AMOUNT = "GHST_AMNT";
    private static final String PENALTY_AMOUNT = "delayamnt";
    private static final String STATUS = "STAT";
    public static final String UNPAYED_AMOUNT = "UNPAY_AMNT";


    @Override
    public Installment mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String installmentNumber = rs.getString(INSTALLMENT_NUMBER);
        Date installmentDate = rs.getDate(INSTALLMENT_DATE);
        final Long amount = rs.getLong(AMOUNT);
        final Long penaltyAmount = rs.getLong(PENALTY_AMOUNT);
        final Long unpayedAmount= rs.getLong(UNPAYED_AMOUNT);
        String statusString = rs.getString(STATUS);
        InstallmentStatus status = InstallmentStatus.NOT_PAYED;
        if (statusString.equals("1")){
            status = InstallmentStatus.PAYED;
        }

        return new Installment(installmentNumber, installmentDate, amount, penaltyAmount,unpayedAmount);
    }
}
