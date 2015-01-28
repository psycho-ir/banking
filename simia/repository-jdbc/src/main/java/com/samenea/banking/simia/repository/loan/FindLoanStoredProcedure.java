package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.simia.model.Loan;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/27/13
 * Time: 2:30 PM
 */

public class FindLoanStoredProcedure extends StoredProcedure {
    private static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_FACNM = "PRM_FACNM";
    public static final String PRM_ACCNO = "PRM_ACCNO";
    public static final String PRM_ACCOWNERNM = "PRM_ACCOWNERNM";
    public static final String PRM_TOTALAMOUNT = "PRM_TOTALAMOUNT";
    public static final String PRM_REMAMOUNT = "PRM_REMAMOUNT";
    public static final String PRM_ACCCLR = "PRM_ACCCLR";
    public static final String PRM_FIRSTUNPAYEDGESTNO = "PRM_FIRSTUNPAYEDGESTNO";
    public static final String PRM_FACGSTNUM = "PRM_FACGSTNUM";
    public static final String PRM_FACGSTDIS = "PRM_FACGSTDIS";
    public static final String PRM_FACUNPAYEDGSTCOUNT = "PRM_FACUNPAYEDGSTCOUNT";
    public static final String PRM_FACKARMOZDAMOUNT = "PRM_FACKARMOZDAMOUNT";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";
    public static final String PRM_STRERR = "PRM_STRERR";
    public static final String PRM_FACTYP = "PRM_FACTYP";
    public static final String PRM_HASRETSOUD = "PRM_HASRETSOUD";
    public static final String PRM_FACDATE = "PRM_FACDATE";
    public static final String PRM_KARMOZDFORMULAID = "PRM_KARMOZDFORMULAID";
    public static final String PRM_KARMOZDPERCENT = "PRM_KARMOZDPERCENT";
    public static final BigDecimal OK =new BigDecimal(1);

    private static final String SP_NAME = "STP_GET_FAC_INFO";
    private Map<String, Object> parameters = new HashMap<String, Object>();

    public FindLoanStoredProcedure(DataSource dataSource) {
        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_FACNM, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_ACCNO, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_ACCOWNERNM, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_TOTALAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_REMAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_ACCCLR, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FIRSTUNPAYEDGESTNO, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FACGSTNUM, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FACGSTDIS, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FACUNPAYEDGSTCOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FACKARMOZDAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_KARMOZDPERCENT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_KARMOZDFORMULAID, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_FACDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_HASRETSOUD, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_FACTYP, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_STRERR, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.DECIMAL));
    }

    public Loan execute(String loanNumber,String type) {
        parameters.put(PRM_FVAM, loanNumber);

        final Map<String, Object> loan = this.execute(parameters);
        if (!loan.get(PRM_SUCCESS).equals(OK)) {
            throw new NotFoundException(String.format("Loan:%s does not exist.", loanNumber));
        }

        return new LoanMapper().map(loan,loanNumber,type);
    }
}
