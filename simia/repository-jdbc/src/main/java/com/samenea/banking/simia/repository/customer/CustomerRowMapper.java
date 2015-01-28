package com.samenea.banking.simia.repository.customer;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.simia.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 2:48 PM
 */

public class CustomerRowMapper implements RowMapper<ICustomer> {

    public static final String CIF = "CIF";
    public static final String FNAME = "FNAME";
    public static final String LNAME = "LNAME";

    @Override
    public ICustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String customerCode = rs.getString(CIF);
        final String name = rs.getString(FNAME);
        final String lName = rs.getString(LNAME);
        return new Customer(name, lName, customerCode, Boolean.FALSE);
    }
}
