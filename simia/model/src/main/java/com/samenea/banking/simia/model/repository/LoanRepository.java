package com.samenea.banking.simia.model.repository;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.ILoan;
import com.samenea.banking.simia.model.Loan;
import com.samenea.banking.simia.model.AghdType;
import com.samenea.banking.simia.model.PayableInstallmentStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 5:20 PM
 */

public interface LoanRepository {
//    List<? extends IInstallment> findInstallments(String loanNumber);
//    Map<String, Object> callInstallmentProcedure(String accno, String loanNumber, String facType, String gstNo, String gstAmount, String payAmount, String docno, String seqno, String userId, String branchCode, String date, String sanadType, String penaltyAmount, String inputResourceType);

    //  String findFacilityType(String loanNumber);

    ILoan findLoan(String loanNumber);

    String payInstallment(String loanNumber, Long accountNumber, String effectiveDate, String userCode, String bankCode, String branchCode);

    ICustomer findCustomer(String loanNumber);


//    PayableInstallmentStatus findLoanStatus(String loanNumber);

//    Map<String, Object> findPayableInstallment(String loanNumber, String installmentNumber, String date);

//    Long findPenaltyAmount(String loanNuber, String installmentAmount, String days);

 /*  Map<String, Object> callClassifiedInstallmentProcedure(String debitAccountNumber, String creditAccountNumber, String debitKarmozAccount,
                                                           String creditKarmozdAccount, String amount, String debitActionType,
                                                           String creditActionType, String inputResourceType, String docno, String seqno, String currentDate,
                                                           String userCode, String loanNumber, String installmentNumber, String payAmount, String penaltyAmount,
                                                           String installmentAmount, String sanadType, String branchCode, String bankCode);

    Integer findDaysDifferent(String date);
    Long findPayedAmount(Loan loan, String date);
    Long findFollowUp(Loan loan, String date);
    Boolean hasPenalty(String loanNumber);
    Integer findRealDays(String installmentDate,String effectiveDate);

    ICustomer findCustomer(String loanNumber);

    String findLastPayedDate(String loanNumber, String installmentNumber, String currentDate);*/
}
