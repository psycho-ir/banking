package com.samenea.banking.simia.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/16/13
 * Time: 6:04 PM
 */

public abstract class BasicRepositoryJDBC {
    @Qualifier("simiaDataSource")
    @Autowired
    private DataSource dataSource;

    final protected DataSource getDataSource() {
        return dataSource;
    }

    final protected JdbcTemplate createNewTemplate() {
        return new JdbcTemplate(this.dataSource);
    }
}
