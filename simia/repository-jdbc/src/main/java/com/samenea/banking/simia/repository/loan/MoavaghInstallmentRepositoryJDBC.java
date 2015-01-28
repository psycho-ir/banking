package com.samenea.banking.simia.repository.loan;

import com.samenea.banking.simia.model.repository.MoavaghInstallmentRepository;
import com.samenea.banking.simia.repository.BasicRepositoryJDBC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Soroosh Sarabadani
 * Date: 3/5/13
 * Time: 3:15 PM
 */

@Repository
public class MoavaghInstallmentRepositoryJDBC extends BasicRepositoryJDBC implements MoavaghInstallmentRepository {

    private static final String FIND_CREDIT_ACCOUNT_NUMBER_QUERY = "select f51 from dbx4_tx42 where f3 = (select f3 from dbx4_tx423 where fvam = ?)";
    private static final String FIND_DEBIT_KARMOZD_QUERY = "select f53 from dbx4_tx42 where f3 = (select f3 from dbx4_tx423 where fvam = ?)";
    private static final String FIND_CREDIT_KARMOZD_QUERY = "select f16 from dbx4_tx42 where f3 = (select f3 from dbx4_tx423 where fvam = ?)";
    private static final String FIND_INPUT_RESOURCE_TYPE_QUERY = "SELECT F84 FROM DBX4_TX423 WHERE FVAM = ?";

    @Override
    public String findCreditAccountNumber(String loanNumber) {
        final JdbcTemplate template = createNewTemplate();
        return template.query(FIND_CREDIT_ACCOUNT_NUMBER_QUERY, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("f51");
            }
        }, loanNumber).get(0);
    }

    @Override
    public String findDebitKarmozdAccount(String loanNumber) {
        final JdbcTemplate template = createNewTemplate();
        return template.query(FIND_DEBIT_KARMOZD_QUERY, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("f53");
            }
        }, loanNumber).get(0);
    }

    @Override
    public String findCreditKarmozdAccount(String loanNumber) {
        final JdbcTemplate template = createNewTemplate();
        return template.query(FIND_CREDIT_KARMOZD_QUERY, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("f16");
            }
        }, loanNumber).get(0);
    }

    @Override
    public String findInputResourceType(String loanNumber) {
        final JdbcTemplate template = createNewTemplate();
        return template.query(FIND_INPUT_RESOURCE_TYPE_QUERY, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("F84");
            }
        }, loanNumber).get(0);
    }
}
