package com.samenea.banking.simia.repository.loan;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 2:34 PM
 */

public class PayInstallmentStoredProcedure extends StoredProcedure {
    private static final String SP_NAME = "stp_gst";
    public static final String PRM_ACCNOBED = "PRM_ACCNOBED";
    public static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_FACTYP = "PRM_FACTYP";
    public static final String PRM_GSTNO = "PRM_GSTNO";
    public static final String PRM_AMOUNTTYP = "PRM_AMOUNTTYP";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";
    public static final String PRM_STRERR = "PRM_STRERR";
    public static final String PRM_SKIPCOMMIT = "PRM_SKIPCOMMIT";
    private final String prm_accnobed = "prm_accnobed";
    private final String prm_fvam = "prm_fvam";
    private final String prm_factyp = "prm_factyp";
    private final String prm_gstno = "prm_gstno";
    private final String prm_amounttyp = "prm_amounttyp";
    private final String prm_actiontyp = "prm_actiontyp";
    private final String prm_jaramount = "prm_jaramount";
    private final String prm_gstamount = "prm_gstamount";
    private final String prm_payamount = "prm_payamount";
    private final String prm_retinterestamount = "prm_retinterestamount";
    private final String prm_retinterestaccno = "prm_retinterestaccno";
    private final String prm_effectivedate = "prm_effectivedate";
    private final String prm_globdate = "prm_globdate";
    private final String prm_dsp = "prm_dsp";
    private final String prm_inputresourcecod = "prm_inputresourcecod";
    private final String prm_inputresourcetype = "prm_inputresourcetype";
    private final String prm_outputresourcecod = "prm_outputresourcecod";
    private final String prm_outputresourcetype = "prm_outputresourcetype";
    private final String prm_currencytype = "prm_currencytype";
    private final String prm_docno = "prm_docno";
    private final String prm_doctype = "prm_doctype";
    private final String prm_bajehno = "prm_bajehno";
    private final String prm_radifno = "prm_radifno";
    private final String prm_usrid = "prm_usrid";
    private final String prm_edtusr = "prm_edtusr";
    private final String prm_gstsanadtyp = "prm_gstsanadtyp";
    private final String prm_seqno = "prm_seqno";
    private final String prm_bankcod = "prm_bankcod";
    private final String prm_branchcod = "prm_branchcod";
    private final String prm_strerr = "prm_strerr";
    private final String prm_success = "prm_success";
    private final String PRM_ERRMSG = "PRM_ERRMSG";
    private final String prm_skipcommit = "prm_skipcommit";

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public PayInstallmentStoredProcedure(DataSource ds) {
        super(ds, SP_NAME);

        declareParameter(new SqlParameter(PRM_ACCNOBED, Types.FLOAT));
        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_FACTYP, Types.INTEGER));
        declareParameter(new SqlParameter(PRM_GSTNO, Types.FLOAT));
        declareParameter(new SqlParameter(PRM_AMOUNTTYP, Types.INTEGER));
        declareParameter(new SqlParameter(prm_actiontyp, Types.INTEGER));
        declareParameter(new SqlParameter(prm_jaramount, Types.FLOAT));
        declareParameter(new SqlParameter(prm_gstamount, Types.FLOAT));
        declareParameter(new SqlParameter(prm_payamount, Types.FLOAT));
        declareParameter(new SqlParameter(prm_retinterestamount, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_retinterestaccno, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_effectivedate, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_globdate, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_dsp, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_inputresourcecod, Types.FLOAT));
        declareParameter(new SqlParameter(prm_inputresourcetype, Types.INTEGER));
        declareParameter(new SqlParameter(prm_outputresourcecod, Types.FLOAT));
        declareParameter(new SqlParameter(prm_outputresourcetype, Types.INTEGER));
        declareParameter(new SqlParameter(prm_currencytype, Types.INTEGER));
        declareParameter(new SqlParameter(prm_docno, Types.FLOAT));
        declareParameter(new SqlParameter(prm_doctype, Types.INTEGER));
        declareParameter(new SqlParameter(prm_bajehno, Types.FLOAT));
        declareParameter(new SqlParameter(prm_radifno, Types.FLOAT));
        declareParameter(new SqlParameter(prm_usrid, Types.INTEGER));
        declareParameter(new SqlParameter(prm_edtusr, Types.INTEGER));
        declareParameter(new SqlParameter(prm_gstsanadtyp, Types.INTEGER));
        declareParameter(new SqlParameter(prm_seqno, Types.FLOAT));
        declareParameter(new SqlParameter(prm_bankcod, Types.FLOAT));
        declareParameter(new SqlParameter(prm_branchcod, Types.FLOAT));
        declareParameter(new SqlOutParameter(PRM_ERRMSG, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.DECIMAL));
        declareParameter(new SqlOutParameter(PRM_STRERR, Types.NVARCHAR));
        declareParameter(new SqlParameter(PRM_SKIPCOMMIT, Types.FLOAT));
        parameters.put(PRM_AMOUNTTYP, 1);
        parameters.put(prm_actiontyp, 50);
        parameters.put(prm_retinterestamount, Types.NULL);
        parameters.put(prm_dsp, Types.NULL);
        parameters.put(prm_inputresourcecod, 0);
        parameters.put(prm_outputresourcecod, 0);
        parameters.put(prm_outputresourcetype, 0);
        parameters.put(prm_currencytype, 1);
        parameters.put(prm_doctype, 5);
        parameters.put(prm_bajehno, 0);
        parameters.put(prm_radifno, 0);
        parameters.put(prm_edtusr, 0);
        parameters.put(prm_bankcod, 1);
        parameters.put(PRM_SKIPCOMMIT, 1);

    }

    public Map<String, Object> execute(String accno, String loanNumber, String facType, String gstNo, String gstAmount, String payAmount, String penaltyAmount,
                                       String docno, String seqno, String userId, String branchCode, String date, String sanadType, String inputResourceType) {
        parameters.put(PRM_ACCNOBED, accno);
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_FACTYP, facType);
        parameters.put(PRM_GSTNO, gstNo);
        parameters.put(prm_gstamount, gstAmount);
        parameters.put(prm_payamount, payAmount);
        parameters.put(prm_retinterestaccno, accno);
        parameters.put(prm_effectivedate, date);
        parameters.put(prm_globdate, date);
        parameters.put(prm_inputresourcetype, inputResourceType);
        parameters.put(prm_docno, docno);
        parameters.put(prm_usrid, userId);
        parameters.put(prm_gstsanadtyp, sanadType);
        parameters.put(prm_seqno, seqno);
        parameters.put(prm_branchcod, branchCode);
        parameters.put(prm_jaramount, penaltyAmount);
        return execute(parameters);

    }
}
