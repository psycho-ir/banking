package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.loan.IInstallment;
import com.samenea.banking.loan.ILoan;
import com.samenea.banking.loan.InstallmentPaymentException;
import com.samenea.banking.simia.model.*;
import com.samenea.banking.simia.model.repository.LoanRepository;
import com.samenea.banking.simia.repository.BasicRepositoryJDBC;
import com.samenea.banking.simia.repository.customer.CustomerRowMapper;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/11/13
 * Time: 5:22 PM
 */
@Repository
public class LoanRepositoryJDBC extends BasicRepositoryJDBC implements LoanRepository {

    private static final String FINDING_FACILITY_TYPE_QUERY = "SELECT F3 FROM DBX4_TX423  WHERE  FVAM = ?";

    private static final String FIND_LOAN_CUSTOMER = "SELECT A.FVAM LOANNO,\n" +
            "       V.F4 DPSTNO, \n" +
            "       V.F5 CIF, \n" +
            "       B.F2 FNAME, \n" +
            "       B.F3 LNAME \n" +
            "  FROM S5SAMENS.DBX4_TX423 A \n" +
            " INNER JOIN S5SAMENS.DBX1_TX18 V ON A.F15 = V.F4 \n" +
            " INNER JOIN S5SAMENS.DBX1_TX11 B ON V.F5 = B.F1 \n" +
            "WHERE A.FVAM = ?";


    @Override
    public ILoan findLoan(String loanNumber) {
        final String facilityType = findFacilityType(loanNumber);
        TelbankGSTProcedure proc = new TelbankGSTProcedure(getDataSource());
        FindLoanStoredProcedure findLoanStoredProcedure = new FindLoanStoredProcedure(getDataSource());
        Loan loan = findLoanStoredProcedure.execute(loanNumber, facilityType);
        String currentDate = SimiaUtils.getCurrentDate(new Date());
        Installment installment = proc.find(loanNumber, currentDate);
        loan.setPayableInstallment(installment);

        return loan;
    }
    @Override
    public String payInstallment(String loanNumber, Long accountNumber, String effectiveDate, String userCode, String bankCode, String branchCode) {
        TelbankGSTProcedure proc = new TelbankGSTProcedure(getDataSource());
        Map<String, Object> results = proc.pay(loanNumber, accountNumber, effectiveDate, userCode, bankCode, branchCode);
        Object success_result = results.get(TelbankGSTProcedure.PRM_SUCCESS);
        if (success_result == null || !success_result.equals(TelbankGSTProcedure.OK)) {
            throw new InstallmentPaymentException((String) success_result, (String) results.get(TelbankGSTProcedure.PRM_ERRMSG));
        }
        return (String) results.get(TelbankGSTProcedure.PRM_SEQNO);
    }

    private String findFacilityType(String loanNumber) {
        JdbcTemplate template = createNewTemplate();
        final List<String> facTypes = template.query(FINDING_FACILITY_TYPE_QUERY, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("F3");
            }
        }, loanNumber);
        if (facTypes == null || facTypes.size() == 0) {
            throw new NotFoundException(String.format("Loan %s doest not exist.", loanNumber));
        }
        return facTypes.get(0);
    }

    @Override
    public ICustomer findCustomer(String loanNumber) {
        final JdbcTemplate template = createNewTemplate();
        final List<ICustomer> customers = template.query(FIND_LOAN_CUSTOMER, new CustomerRowMapper(), loanNumber);
        if (customers == null || customers.size() == 0) {
            throw new NotFoundException(String.format("Customer or Loan for LoanNumber:%s does not exist", loanNumber));
        }
        return customers.get(0);
    }


}
