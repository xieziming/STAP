package com.xieziming.stap.core.model.comment.dao;

import com.xieziming.stap.core.model.comment.dto.CommentDto;
import com.xieziming.stap.core.model.comment.pojo.Comment;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 8/12/16.
 */
public class CommentDao {
    private static Logger logger = LoggerFactory.getLogger(CommentDao.class);

    public CommentDto findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT+ " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, commentDtoRowMapper);
    }

    public List<CommentDto> findAllByExecutionPlanId(int executionPlanId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Execution_Plan_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionPlanId}, commentDtoRowMapper);
    }
    public List<CommentDto> findAllByExecutionId(int executionId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Execution_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{executionId}, commentDtoRowMapper);
    }

    public List<CommentDto> findAllByTestCaseId(int testCaseId) {
        String sql = "SELECT * FROM " + StapDbTables.COMMENT + " WHERE Test_Case_Id=?";
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[]{testCaseId}, commentDtoRowMapper);
    }

    public void add(Comment comment) {
        String sql = "INSERT INTO "+ StapDbTables.COMMENT+" SET Parent_Comment_Id=?, Test_Case_Id=?, Execution_Plan_Id=?, Execution_Id=?, Content=?, User_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{comment.getParentCommentId(), comment.getTestCaseId(), comment.getExecutionPlanId(), comment.getExecutionId(), comment.getContent(), comment.getUserId()});
    }

    public void update(Comment comment) {
        String sql = "UPDATE "+StapDbTables.COMMENT+" SET Parent_Comment_Id=?, Test_Case_Id=?, Execution_Plan_Id=?, Test_Case_Id=?, Content=?, User_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{comment.getParentCommentId(), comment.getTestCaseId(), comment.getExecutionPlanId(), comment.getExecutionId(), comment.getContent(), comment.getUserId(), comment.getId()});
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByExecutionPlanId(int executionPlanId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Execution_Plan_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionPlanId});
    }

    public void deleteAllByExecutionId(int executionId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Execution_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{executionId});
    }

    public void deleteAllByTestCaseId(int testCaseId) {
        String sql = "DELETE FROM "+StapDbTables.EXECUTION+" WHERE Test_Case_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testCaseId});
    }

    private RowMapper<CommentDto> commentDtoRowMapper = new RowMapper<CommentDto>() {
        public CommentDto mapRow(ResultSet resultSet, int i) throws SQLException {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(resultSet.getInt("Id"));
            commentDto.setContent(resultSet.getString("Content"));
            commentDto.setUserId(resultSet.getInt("User_Id"));
            commentDto.setTime(resultSet.getTimestamp("Time"));
            return commentDto;
        }
    };
}
