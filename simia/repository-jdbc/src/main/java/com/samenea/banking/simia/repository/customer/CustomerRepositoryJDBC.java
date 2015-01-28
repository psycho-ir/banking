package com.samenea.banking.simia.repository.customer;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.simia.model.repository.CustomerRepository;
import com.samenea.banking.simia.repository.BasicRepositoryJDBC;
import com.samenea.commons.component.model.exceptions.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 2:31 PM
 */
@Repository
public class CustomerRepositoryJDBC extends BasicRepositoryJDBC implements CustomerRepository {
    @Override
    public ICustomer findCustomer(String customerId, String username, String password) {
        JdbcTemplate template = createNewTemplate();
        List<ICustomer> customers = template.query("SELECT A.CIF, " +
                "       A.FNAME, " +
                "       A.LNAME, " +
                "       A.USERNAME, " +
                "       A.PASSWORD, " +
                "       UPPER(A.ACTIVE) ACTIVE, " +
                "       UPPER(A.USERLOCK) USERLOCK " +
                "  FROM EUSERS A " +
                " WHERE A.USERNAME = ? " +
                "   AND A.PASSWORD = ? " +
                "   AND A.CIF = ?", new CustomerRowMapper(), username, password, customerId);
        if (customers == null || customers.size() == 0) {
            throw new NotFoundException(String.format("Customer with id: %s username: %s password: ****** does not exist.", customerId, username));
        }
        return customers.get(0);
    }

    @Override
    public List<ICustomer> findCustomersOfDeposit(String depositNumber) {
        JdbcTemplate template = createNewTemplate();
        List<ICustomer> customers = template.query("SELECT V.DPSTNO, \n" +
                "       V.OWNER_ID CIF, \n" +
                "       B.F2 FNAME, \n" +
                "       B.F3 LNAME\n" +
                "       \n" +
                "  FROM (SELECT A.F5 OWNER_ID, A.F4 DPSTNO\n" +
                "          FROM S5SAMENS.DBX1_TX18 A\n" +
                "        UNION ALL\n" +
                "        SELECT A.F6 OWNER_ID, A.F4 DPSTNO\n" +
                "          FROM S5SAMENS.DBX1_TX18 A\n" +
                "         WHERE A.F6 > 0\n" +
                "        UNION ALL\n" +
                "        SELECT A.F7 OWNER_ID, A.F4 DPSTNO\n" +
                "          FROM S5SAMENS.DBX1_TX18 A\n" +
                "         WHERE A.F7 > 0\n" +
                "        UNION ALL\n" +
                "        SELECT A.F8 OWNER_ID, A.F4 DPSTNO\n" +
                "          FROM S5SAMENS.DBX1_TX18 A\n" +
                "         WHERE A.F8 > 0\n" +
                "        UNION ALL\n" +
                "        SELECT A.F9 OWNER_ID, A.F4 DPSTNO\n" +
                "          FROM S5SAMENS.DBX1_TX18 A\n" +
                "         WHERE A.F9 > 0) V\n" +
                " INNER JOIN S5SAMENS.DBX1_TX11 B ON V.OWNER_ID = B.F1\n" +
                "WHERE V.DPSTNO = ?", new CustomerRowMapper(), depositNumber);
        if (customers == null || customers.size() == 0) {
            throw new NotFoundException(String.format("Customer or depositNumber {} does not exist.", depositNumber));
        }
        return customers;
    }
}
