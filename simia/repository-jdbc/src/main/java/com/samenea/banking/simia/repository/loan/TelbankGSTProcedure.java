package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.simia.model.Installment;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/3/13
 * Time: 11:54 AM
 */

public class TelbankGSTProcedure extends StoredProcedure {
    Logger logger = LoggerFactory.getLogger(TelbankGSTProcedure.class);
    public static final String OK = "1";
    private static final String SP_NAME = "stp_telbank_gst";
    public static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_ACCNO = "PRM_ACCNO";
    public static final String PRM_GLOBDATE = "PRM_GLOBDATE";
    public static final String PRM_USRCOD = "PRM_USRCOD";
    public static final String PRM_BANKCOD = "PRM_BANKCOD";
    public static final String PRM_BRANCHCOD = "PRM_BRANCNHCOD";
    public static final String PRM_INFO = "PRM_INFO";
    public static final String PRM_GSTTYP = "PRM_GSTTYP";
    public static final String PRM_FIRSTUNPAYEDGESTNO = "PRM_FIRSTUNPAYEDGESTNO";
    public static final String PRM_AMOUNT = "PRM_AMOUNT";
    public static final String PRM_JARIMEH = "PRM_JARIMEH";
    public static final String PRM_SEQNO = "PRM_SEQNO";
    public static final String PRM_CUSTOMERNAME = "PRM_CUSTOMERNAME";
    public static final String PRM_CUSTOMERFAMILY = "PRM_CUSTOMERFAMILY";
    public static final String PRM_GSTDATE = "PRM_GSTDATE";
    public static final String PRM_STRERR = "PRM_STRERR";
    public static final String PRM_ERRMSG = "PRM_ERRMSG";
    public static final String PRM_USERAMOUNT = "PRM_USERAMOUNT";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";
    public static final String PRM_USERDATE = "PRM_USERDATE";
    public static final String PRM_SKIPCOMMIT = "PRM_SKIPCOMMIT";

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public TelbankGSTProcedure(DataSource dataSource) {
        super(dataSource, SP_NAME);


        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_ACCNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GLOBDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_USRCOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_BANKCOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_BRANCHCOD, Types.VARCHAR));
        declareParameter(new SqlInOutParameter(PRM_INFO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTTYP, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_FIRSTUNPAYEDGESTNO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_AMOUNT, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_JARIMEH, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SEQNO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_CUSTOMERNAME, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_CUSTOMERFAMILY, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_STRERR, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_ERRMSG, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_USERAMOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_USERDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_SKIPCOMMIT, Types.VARCHAR));

    }

    public Installment find(String loanNumber, String effectiveDate) {
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_GLOBDATE, effectiveDate);
        parameters.put(PRM_INFO, "0");
        parameters.put(PRM_ACCNO, "0");
        parameters.put(PRM_USERDATE, effectiveDate);
        parameters.put(PRM_USRCOD, "0");
        parameters.put(PRM_BANKCOD, "0");
        parameters.put(PRM_BRANCHCOD, "0");
        parameters.put(PRM_SKIPCOMMIT, "1");
        parameters.put(PRM_USERAMOUNT, "0");

        Map<String, Object> results = this.execute(parameters);
        logger.info("Installment finding results: {}", results.toString());


        Object firstUnpayedGhestNo = results.get(PRM_FIRSTUNPAYEDGESTNO);
        if (firstUnpayedGhestNo == null || (firstUnpayedGhestNo.equals(new BigDecimal(0)))) {
            return null;
        }
        String firstUnpayedGstNo = firstUnpayedGhestNo.toString();
        Date gstDate = SimiaUtils.getDate((String) results.get(PRM_GSTDATE));
        Long amount = Long.valueOf(results.get(PRM_AMOUNT).toString());
        Long penalty = Long.valueOf(results.get(PRM_JARIMEH).toString());


        Installment installment = new Installment(firstUnpayedGstNo, gstDate, amount, penalty, 0l);
        return installment;

    }

    public Map<String, Object> pay(String loanNumber, Long accountNumber, String effectiveDate, String userCode, String bankCode, String branchCode) {
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_GLOBDATE, effectiveDate);
        parameters.put(PRM_INFO, "2");
        parameters.put(PRM_ACCNO, accountNumber);
        parameters.put(PRM_USERDATE, effectiveDate);
        parameters.put(PRM_USRCOD, userCode);
        parameters.put(PRM_BANKCOD, bankCode);
        parameters.put(PRM_BRANCHCOD, branchCode);
        parameters.put(PRM_SKIPCOMMIT, "1");
        parameters.put(PRM_USERAMOUNT, "0");

        Map<String, Object> results = this.execute(parameters);
        logger.info("Installment payment results: {}", results.toString());
        return results;
    }
}
