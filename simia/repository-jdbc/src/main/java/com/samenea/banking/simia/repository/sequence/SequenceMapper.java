package com.samenea.banking.simia.repository.sequence;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/17/13
 * Time: 1:26 PM
 */

public class SequenceMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("SEQ");
    }
}
