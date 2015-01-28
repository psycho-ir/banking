package com.samenea.banking.simia.repository.sequence;

import com.samenea.banking.simia.model.repository.SequenceRepository;
import com.samenea.banking.simia.repository.BasicRepositoryJDBC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 2/12/13
 * Time: 4:26 PM
 */
@Repository
public class SequenceRepositoryJDBC extends BasicRepositoryJDBC implements SequenceRepository {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getNewSequence(String branchCode, String date) {
        JdbcTemplate template = new JdbcTemplate(getDataSource());
        final List<String> query = template.query("SELECT fun_make_seqno(1,?,?) AS SEQ FROM DUAL", new SequenceMapper(), branchCode, date);

        if (query == null || query.size() != 1) {
            throw new IllegalStateException("Error in getting sequence number from simia.");
        }
        return query.get(0);

    }
}
