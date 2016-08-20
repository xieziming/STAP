package com.xieziming.stap.core.model.comment.dao;

import com.xieziming.stap.core.model.comment.pojo.Comment;
import com.xieziming.stap.core.notification.NotificationManager;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
@Component
public class CommentDao {
    private static Logger logger = LoggerFactory.getLogger(CommentDao.class);
    @Autowired
    private NotificationManager notificationManager;

    public Comment findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, commentRowMapper);
    }

    public List<Comment> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, commentRowMapper);
    }
    public List<Comment> findAllByExecutionId(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, commentRowMapper);
    }

    public List<Comment> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, commentRowMapper);
    }

    public void add(Comment comment) {
        String sql = "INSERT INTO "+ StapDbTables.COMMENT+" SET Test_Case_Id=?, Execution_Plan_Id=?, Execution_Id=?, Content=?, User_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{ comment.getTestCaseId(), comment.getExecutionPlanId(), comment.getExecutionId(), comment.getContent(), comment.getUserId()});
    }

    public void update(Comment comment) {
        String sql = "UPDATE "+StapDbTables.COMMENT+" SET Test_Case_Id=?, Execution_Plan_Id=?, Test_Case_Id=?, Content=?, User_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{ comment.getTestCaseId(), comment.getExecutionPlanId(), comment.getExecutionId(), comment.getContent(), comment.getUserId(), comment.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.COMMENT+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.COMMENT+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.COMMENT+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByTestCaseId(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.COMMENT+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    private RowMapper<Comment> commentRowMapper = new RowMapper<Comment>() {
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("Id"));
            comment.setTestCaseId(resultSet.getInt("Test_Case_Id"));
            comment.setExecutionPlanId(resultSet.getInt("Execution_Plan_Id"));
            comment.setExecutionId(resultSet.getInt("Execution_Id"));
            comment.setContent(resultSet.getString("Content"));
            comment.setUserId(resultSet.getInt("User_Id"));
            comment.setTime(resultSet.getTimestamp("Time"));
            return comment;
        }
    };
}
