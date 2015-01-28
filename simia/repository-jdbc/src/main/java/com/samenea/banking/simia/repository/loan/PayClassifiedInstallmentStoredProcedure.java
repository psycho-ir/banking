package com.samenea.banking.simia.repository.loan;//import org.slf4j.Logger;

import com.samenea.commons.component.utils.log.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/4/13
 * Time: 12:42 PM
 */

public class PayClassifiedInstallmentStoredProcedure extends StoredProcedure {
    private static final String SP_NAME = "STP_F_091";
    public static final String PRM_ACCCODBED = "PRM_ACCCODBED";
    public static final String PRM_ACCCODBES = "PRM_ACCCODBES";
    public static final String PRM_KARMOZDACCCODBED = "PRM_KARMOZDACCCODBED";
    public static final String PRM_KARMOZDACCCODBES = "PRM_KARMOZDACCCODBES";
    public static final String PRM_AMOUNT = "PRM_AMOUNT";
    public static final String PRM_ACTIONTYPBED = "PRM_ACTIONTYPBED";
    public static final String PRM_ACTIONTYPBES = "PRM_ACTIONTYPBES";
    public static final String PRM_INPUTRESOURCECOD = "PRM_INPUTRESOURCECOD";
    public static final String PRM_OUTPUTRESOURCETYP = "PRM_OUTPUTRESOURCETYP";
    public static final String PRM_INPUTRESOURCETYP = "PRM_INPUTRESOURCETYP";
    public static final String PRM_CURRENCYTYP = "PRM_CURRENCYTYP";
    public static final String PRM_DOCNO = "PRM_DOCNO";
    public static final String PRM_DOCTYPE = "PRM_DOCTYPE";
    public static final String PRM_GISHCOD = "PRM_GISHCOD";
    public static final String PRM_SEQNO = "PRM_SEQNO";
    public static final String PRM_GLOBDATE = "PRM_GLOBDATE";
    public static final String PRM_USRCOD = "PRM_USRCOD";
    public static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_GSTNO = "PRM_GSTNO";
    public static final String PRM_JARAMOUNT = "PRM_JARAMOUNT";
    public static final String PRM_PAYAMOUNT = "PRM_PAYAMOUNT";
    public static final String PRM_EFFECTIVEDATE = "PRM_EFFECTIVEDATE";
    public static final String PRM_GSTAMOUNT = "PRM_GSTAMOUNT";
    public static final String PRM_GSTSANADTYP = "PRM_GSTSANADTYP";
    public static final String PRM_BRANCHCODE = "PRM_BRANCHCODE";
    public static final String PRM_BANKCOD = "PRM_BANKCOD";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";
    public static final String PRM_STRERR = "PRM_STRERR";
    public static final String PRM_ERRMSG = "PRM_ERRMSG";
    public static final String PRM_OUTPUTRESOURCECOD = "PRM_OUTPUTRESOURCECOD";
    private final String PRM_SKIPCOMMIT = "PRM_SKIPCOMMIT";
    private Logger logger = LoggerFactory.getLogger(PayClassifiedInstallmentStoredProcedure.class);

    Map<String, String> parameters = new HashMap<String, String>();

    public PayClassifiedInstallmentStoredProcedure(DataSource dataSource) {
        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(PRM_ACCCODBED, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_ACCCODBES, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_KARMOZDACCCODBED, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_KARMOZDACCCODBES, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_AMOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_ACTIONTYPBED, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_ACTIONTYPBES, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_INPUTRESOURCECOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_INPUTRESOURCETYP, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_OUTPUTRESOURCETYP, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_OUTPUTRESOURCECOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_CURRENCYTYP, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_DOCNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_DOCTYPE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GISHCOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_SEQNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GLOBDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_USRCOD, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GSTNO, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_EFFECTIVEDATE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_PAYAMOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_JARAMOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GSTAMOUNT, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GSTSANADTYP, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_BRANCHCODE, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_BANKCOD, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_STRERR, Types.VARCHAR));
        declareParameter(new SqlOutParameter(PRM_ERRMSG, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_SKIPCOMMIT, Types.VARCHAR));

    }

    public Map<String, Object> execute(String debitAccountNumber, String creditAccountNumber, String debitKarmozAccount, String creditKarmozdAccount,
                                       String amount, String debitActionType, String creditActionType, String inputResourceType, String docno,
                                       String seqno, String currentDate, String userCode, String loanNumber, String installmentNumber,
                                       String payAmount, String penaltyAmount, String installmentAmount, String sanadType,
                                       String branchCode, String bankCode) {

        parameters.put(PRM_ACCCODBED, debitAccountNumber);
        parameters.put(PRM_ACCCODBES, creditAccountNumber);
        parameters.put(PRM_KARMOZDACCCODBED, debitKarmozAccount);
        parameters.put(PRM_KARMOZDACCCODBES, creditKarmozdAccount);
        parameters.put(PRM_AMOUNT, amount);
        parameters.put(PRM_ACTIONTYPBED, debitActionType);
        parameters.put(PRM_ACTIONTYPBES, creditActionType);
        parameters.put(PRM_INPUTRESOURCECOD, "0");
        parameters.put(PRM_INPUTRESOURCETYP, inputResourceType);
        parameters.put(PRM_OUTPUTRESOURCECOD, "0");
        parameters.put(PRM_OUTPUTRESOURCETYP, "0");
        parameters.put(PRM_CURRENCYTYP, "1");
        parameters.put(PRM_DOCNO, docno);
        parameters.put(PRM_DOCTYPE, "4");
        parameters.put(PRM_GISHCOD, "127");
        parameters.put(PRM_SKIPCOMMIT, "1");
        parameters.put(PRM_SEQNO, seqno);
        parameters.put(PRM_GLOBDATE, currentDate);
        parameters.put(PRM_USRCOD, userCode);
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_GSTNO, installmentNumber);
        parameters.put(PRM_EFFECTIVEDATE, currentDate);
        parameters.put(PRM_PAYAMOUNT, payAmount);
        parameters.put(PRM_JARAMOUNT, penaltyAmount);
        parameters.put(PRM_GSTAMOUNT, installmentAmount);
        parameters.put(PRM_GSTSANADTYP, sanadType);
        parameters.put(PRM_BRANCHCODE, branchCode);
        parameters.put(PRM_BANKCOD, bankCode);

        return execute(parameters);
    }

}
