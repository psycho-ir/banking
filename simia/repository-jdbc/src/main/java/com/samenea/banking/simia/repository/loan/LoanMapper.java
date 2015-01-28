package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.simia.model.AghdType;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.model.SimiaUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 2:11 PM
 */

public class LoanMapper {

    public Loan map(Map<String, Object> loanMap, String loanNumber, String type) {
        final Date facDate = SimiaUtils.getDate(loanMap.get(FindLoanStoredProcedure.PRM_FACDATE).toString());
        final String rate = loanMap.get(FindLoanStoredProcedure.PRM_KARMOZDPERCENT).toString();
        final Long originalAmount = Long.parseLong(loanMap.get(FindLoanStoredProcedure.PRM_TOTALAMOUNT).toString());
        final Long remainedAmount = Long.parseLong(loanMap.get(FindLoanStoredProcedure.PRM_REMAMOUNT).toString());
        final AghdType aghdType = AghdType.enumOf(Integer.valueOf(loanMap.get(FindLoanStoredProcedure.PRM_FACTYP).toString()));
        final String loanDescription = loanMap.get(FindLoanStoredProcedure.PRM_FACNM).toString();
        final Loan loan = new Loan(loanNumber, facDate, rate, originalAmount, remainedAmount,type,loanDescription,aghdType);
        loan.setPayableInstallmenNumber(loanMap.get(FindLoanStoredProcedure.PRM_FIRSTUNPAYEDGESTNO).toString());
        return loan;
    }
}
