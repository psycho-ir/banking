package com.samenea.banking.simia.repository.deposit;

import com.samenea.banking.deposit.IDeposit;
import com.samenea.banking.simia.model.Deposit;
import com.samenea.banking.simia.model.repository.DepositRepository;
import com.samenea.banking.simia.repository.BasicRepositoryJDBC;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 5:25 PM
 */

@Repository
public class DepositRepositoryJDBC extends BasicRepositoryJDBC implements DepositRepository {


    private static final String FINDING_ACTIVE_DEPOSITS_QUERY = "SELECT A.F4 DPSTNO, " +
            "   A.F65    DPSTBRANCHCODE ," +
            "       B.F2 DPSTDESC , " +
            "       D.F1 OWNER_ID , " +
            "       C.F4 REMAMNT " +
            "  FROM DBX1_TX18 A " +
            " INNER JOIN DBX1_TX11 D ON A.F5 = D.F1 " +
            " INNER JOIN DBX1_TX40 B ON A.F3 = B.F3 " +
            " INNER JOIN DBX2_TX24 C ON C.F2 = A.F4 " +
            " WHERE B.F19 = 1 AND A.F57 = 0 AND A.F51 = 0  AND A.F5 = ?";

    private static final String DEPOSIT_FINDING_QUERY = "SELECT A.F4 DPSTNO, " +
            "       B.F2 DPSTDESC , " +
            "       A.F65 DPSTBRANCHCODE," +
            "       D.F1 OWNER_ID , " +
            "       C.F4 REMAMNT " +
            "  FROM DBX1_TX18 A " +
            " INNER JOIN DBX1_TX11 D ON A.F5 = D.F1 " +
            " INNER JOIN DBX1_TX40 B ON A.F3 = B.F3 " +
            " INNER JOIN DBX2_TX24 C ON C.F2 = A.F4 " +
            " WHERE B.F19 = 1 AND A.F57 = 0 AND A.F51 = 0  AND A.F4 = ?";

    private static final String VALIDATION_QUERY = "SELECT" +
            "       CASE\n" +
            "         WHEN A.F57 = 0 AND A.F51 = 0 AND B.F19 = 1 THEN\n" +
            "          1\n" +
            "         ELSE\n" +
            "          0\n" +
            "       END IS_CHARGABLE\n" +
            "  FROM S5SAMENS.DBX1_TX18 A\n" +
            " INNER JOIN S5SAMENS.DBX1_TX11 D ON A.F5 = D.F1\n" +
            " INNER JOIN S5SAMENS.DBX1_TX40 B ON A.F3 = B.F3\n" +
            " WHERE \n" +
            " A.F4 = ?";

    @Override
    public List<? extends IDeposit> findActiveDeposits(String customerId) {
        JdbcTemplate template = createNewTemplate();
        return template.query(FINDING_ACTIVE_DEPOSITS_QUERY, new DepositRowMapper(), customerId);

    }

    @Override
    public Map<String, Object> insertRowOfCharge(String accno, String acccode, Integer amount, String description,
                                                 String docno, String seqno, String userId, String docDate, String branchCode, String creditBranchCode) {
        ChargeDepositStoredProcedure proc = new ChargeDepositStoredProcedure(getDataSource());

        return proc.execute(accno, acccode, amount, description, docno, seqno, userId, docDate, branchCode, creditBranchCode);
    }

    @Override
    public Deposit findDeposit(String depositNumber) {

        JdbcTemplate template = createNewTemplate();
        final List<Deposit> deposits = template.query(DEPOSIT_FINDING_QUERY, new DepositRowMapper(), depositNumber);
        if (deposits == null || deposits.size() == 0) {
            throw new NotFoundException(String.format("Deposit with number: %s does not exist.", depositNumber));
        }
        return deposits.get(0);

    }

    @Override
    public Boolean isDepositValidForCharging(String depositNumber) {


        JdbcTemplate template = createNewTemplate();
        final List<Boolean> chargable = template.query(VALIDATION_QUERY, new RowMapper<Boolean>() {
            @Override
            public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getBoolean("IS_CHARGABLE");
            }
        }, depositNumber);

        if (chargable == null || chargable.size() == 0) {
            throw new NotFoundException(String.format("Deposit with number: %s does not exist.", depositNumber));
        }
        return chargable.get(0);

    }
}
