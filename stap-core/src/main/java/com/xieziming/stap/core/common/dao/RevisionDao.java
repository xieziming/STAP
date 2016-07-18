package com.xieziming.stap.core.common.dao;

import com.xieziming.stap.core.common.pojo.Revision;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class RevisionDao {

    public void add(Revision revision) {
        String sql = "INSERT INTO "+StapDbTables.TEST_CASE_REVISION+" SET Source=?, Reference=?, Content=?, Operator=?, `Time`=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{revision.getSource(), revision.getReference(), revision.getContent(), revision.getOperator(), revision.getTime()});
    }

    public void update(Revision revision) {
        String sql = "UPDATE "+StapDbTables.TEST_CASE_REVISION+" SET Source=?, Reference=?, Content=?, Operator=?, `Time`=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{revision.getSource(), revision.getReference(), revision.getContent(), revision.getOperator(), revision.getTime(), revision.getId()});
    }

    public void delete(Revision revision) {
        String sql = "DELETE FROM "+StapDbTables.TEST_CASE_REVISION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{revision.getId()});
    }

    public Revision findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, revisionRowMapper);
    }

    public Revision findAll(String source, String reference) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_CASE_REVISION + " WHERE Source=? AND Reference=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{source, reference}, revisionRowMapper);
    }

    private RowMapper<Revision> revisionRowMapper = new RowMapper<Revision>() {
        public Revision mapRow(ResultSet resultSet, int i) throws SQLException {
            Revision revision = new Revision();
            revision.setId(resultSet.getInt("Id"));
            revision.setSource(resultSet.getString("Source"));
            revision.setReference(resultSet.getString("Reference"));
            revision.setContent(resultSet.getString("Content"));
            revision.setOperator(resultSet.getString("Operator"));
            revision.setTime(resultSet.getTimestamp("Time"));
            return revision;
        }
    };
}
