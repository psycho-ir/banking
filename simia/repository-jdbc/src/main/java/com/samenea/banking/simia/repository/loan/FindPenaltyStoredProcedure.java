package com.samenea.banking.simia.repository.loan;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by soroosh on 1/18/14.
 */
public class FindPenaltyStoredProcedure extends StoredProcedure {
    private static final String SP_NAME = "STP_GET_JARIMEH";
    public static final String PRM_FVAM = "PRM_FVAM";
    public static final String PRM_GSTAMOUNT = "PRM_GSTAMOUNT";
    public static final String PRM_DELAYDAY = "PRM_DELAYDAY";
    public static final String PRM_JARIMEAMOUNT = "PRM_JARIMEAMOUNT";
    public static final String PRM_SUCCESS = "PRM_SUCCESS";

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public FindPenaltyStoredProcedure(DataSource dataSource) {
        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(PRM_FVAM, Types.VARCHAR));
        declareParameter(new SqlParameter(PRM_GSTAMOUNT, Types.DECIMAL));
        declareParameter(new SqlParameter(PRM_DELAYDAY, Types.FLOAT));
        declareParameter(new SqlOutParameter(PRM_JARIMEAMOUNT, Types.INTEGER));
        declareParameter(new SqlOutParameter(PRM_SUCCESS, Types.DECIMAL));

    }

    public Map<String, Object> execute(String loanNumber, String installmentAmount, Integer delayDays) {
        parameters.put(PRM_FVAM, loanNumber);
        parameters.put(PRM_GSTAMOUNT, installmentAmount);
        parameters.put(PRM_DELAYDAY, delayDays);
        return execute(parameters);
    }
}
