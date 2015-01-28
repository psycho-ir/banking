package com.samenea.banking.simia.repository.deposit;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Wraps charge deposit store procedure of simia for connection to database and getting output.
 *
 * @author: Soroosh Sarabadani
 * Date: 1/17/13
 * Time: 10:17 AM
 */

public class ChargeDepositStoredProcedure extends StoredProcedure {
    private static final String SP_NAME = "stp_insert_row";
    private final String prm_accno = "prm_accno";
    private final String prm_acccod = "prm_acccod";
    private final String prm_amount = "prm_amount";
    private final String prm_amounttyp = "prm_amounttyp";
    private final String prm_acttype = "prm_acttype";
    private final String prm_inputresourcecod = "prm_inputresourcecod";
    private final String prm_inputresourcetype = "prm_inputresourcetype";
    private final String prm_outputresourcecod = "prm_outputresourcecod";
    private final String prm_outputresourcetype = "prm_outputresourcetype";
    private final String prm_currencytype = "prm_currencytype";
    private final String prm_docno = "prm_docno";
    private final String prm_doctype = "prm_doctype";
    private final String prm_bajehno = "prm_bajehno";
    private final String prm_seqno = "prm_seqno";
    private final String prm_radifno = "prm_radifno";
    private final String prm_usrid = "prm_usrid";
    private final String prm_edtusr = "prm_edtusr";
    private final String prm_globdate = "prm_globdate";
    private final String prm_ischeckremamount = "prm_ischeckremamount";
    private final String prm_branchcod = "prm_branchcod";
    private final String prm_success = "PRM_SUCCESS";
    private final String prm_strerr = "PRM_STRERR";
    private final String prm_r1 = "prm_r1";
    private final String prm_dsp = "prm_dsp";

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public ChargeDepositStoredProcedure(DataSource ds) {
        super(ds, SP_NAME);

        declareParameter(new SqlParameter(prm_accno, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_acccod, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_amount, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_amounttyp, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_dsp, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_acttype, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_inputresourcecod, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_inputresourcetype, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_outputresourcecod, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_outputresourcetype, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_currencytype, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_docno, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_doctype, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_bajehno, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_seqno, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_radifno, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_usrid, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_edtusr, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_globdate, Types.VARCHAR));
        declareParameter(new SqlParameter(prm_ischeckremamount, Types.DECIMAL));
        declareParameter(new SqlParameter(prm_branchcod, Types.DECIMAL));
        declareParameter(new SqlOutParameter(prm_success, Types.DECIMAL));
        declareParameter(new SqlOutParameter(prm_strerr, Types.NVARCHAR));
        declareParameter(new SqlParameter(prm_r1, Types.VARCHAR));
        parameters.put(prm_acttype, 1);
        parameters.put(prm_amounttyp, 1);

        parameters.put(prm_outputresourcecod, 0);
        parameters.put(prm_outputresourcetype, 0);
        parameters.put(prm_currencytype, 1);
        parameters.put(prm_doctype, 4);
        parameters.put(prm_bajehno, 0);
        parameters.put(prm_radifno, 0);
        parameters.put(prm_edtusr, 0);
        parameters.put(prm_ischeckremamount, 0);
        parameters.put(prm_r1, Types.NULL);

    }

    public Map<String, Object> execute(String accno, String acccode, Integer amount, String description,
                                       String docno, String seqno, String userId, String docDate, String branchCode, String creditBranchCode) {
        parameters.put(prm_accno, accno);
        parameters.put(prm_acccod, acccode);
        parameters.put(prm_amount, amount);
        parameters.put(prm_dsp, description);
        parameters.put(prm_docno, docno);
        parameters.put(prm_inputresourcecod, creditBranchCode);
        parameters.put(prm_seqno, seqno);
        parameters.put(prm_usrid, userId);
        parameters.put(prm_globdate, docDate);
        parameters.put(prm_branchcod, branchCode);
        parameters.put(prm_inputresourcetype, 0);


        return execute(parameters);

    }

}
