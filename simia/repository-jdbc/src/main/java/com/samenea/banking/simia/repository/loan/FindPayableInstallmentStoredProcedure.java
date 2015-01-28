package com.samenea.banking.simia.repository.loan;
//import org.slf4j.Logger;
//import com.samenea.commons.component.utils.log.LoggerFactory;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/3/13
 * Time: 10:24 AM
 */

public class FindPayableInstallmentStoredProcedure extends StoredProcedure {
    private static final String SP_NAME = "STP_GST_INFO";

    private static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";
    public static final String PRM_STRERR = "PRM_STRERR";
    public static final String PRM_GSTNO = "PRM_GSTNO";
    public static final String PRM_GSTDATE = "PRM_GSTDATE";
    public static final String PRM_GSTAMOUNT = "PRM_GSTAMOUNT";
    public static final String PRM_GSTEFFECTIVEDATE = "PRM_GSTEFFECTIVEDATE";
    public static final String PRM_GSTPAYEDAMOUNT = "PRM_GSTPAYEDAMOUNT";
    public static final String PRM_JARAMOUNT = "PRM_JARAMOUNT";
    public static final String PRM_JARPAYEDAMOUNT = "PRM_JARPAYEDAMOUNT";
    public static final String PRM_GSTKARMOZD = "PRM_GSTKARMOZD";
    public static final String PRM_GSTPAYEDKARMOZD = "PRM_GSTPAYEDKARMOZD";
    public static final String PRM_GSTSEQNO = "PRM_GSTSEQNO";
    public static final String PRM_JARPAYEDDATE = "PRM_JARPAYEDDATE";
    public static final String PRM_JARSEQNO = "PRM_JARSEQNO";
    public static final String PRM_GSTSANADTYP = "PRM_GSTSANADTYP";
    public static final String PRM_GSTSANADDATE = "PRM_GSTSANADDATE";
    public static final String PRM_CALCJARAMOUNT = "PRM_CALCJARAMOUNT";
    public static final String PRM_GSTPREVPAYEDDATE = "PRM_GSTPREVPAYEDDATE";
    public static final String PRM_GLOBDATE = "PRM_GLOBDATE";
    public static final String PRM_USESANADTYP = "PRM_USESANADTYP";

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public FindPayableInstallmentStoredProcedure(DataSource dataSource) {
        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GSTNO, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_GSTDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_GSTEFFECTIVEDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTPAYEDAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_JARAMOUNT, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_JARPAYEDAMOUNT, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_GSTKARMOZD, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTPAYEDKARMOZD, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTSEQNO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_JARPAYEDDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_JARSEQNO, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTSANADTYP, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTSANADDATE, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_CALCJARAMOUNT, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_GSTPREVPAYEDDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GLOBDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_USESANADTYP, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_STRERR, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.VARCHAR));
    }

    public Map<String, Object> execute(String loanNumber, String installmentNumber, String date) {
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_GSTNO, Integer.parseInt(installmentNumber));
        parameters.put(PRM_GLOBDATE, date);
        parameters.put(PRM_USESANADTYP, "0");
        return execute(parameters);

    }

}
