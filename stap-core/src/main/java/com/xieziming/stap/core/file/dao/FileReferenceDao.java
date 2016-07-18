package com.xieziming.stap.core.file.dao;

import com.xieziming.stap.core.file.pojo.FileReference;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Suny on 5/21/16.
 */
@Component
public class FileReferenceDao {

    public void add(FileReference fileReference) {
        String sql = "INSERT INTO "+StapDbTables.FILE_REPO+" SET Name=?, URI=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{fileReference.getName(), fileReference.getUri()});
    }

    public void update(FileReference fileReference) {
        String sql = "UPDATE "+StapDbTables.FILE_REPO+" SET Name=?, URI=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{fileReference.getName(), fileReference.getUri(), fileReference.getId()});
    }

    public void delete(FileReference fileReference) {
        String sql = "DELETE FROM "+StapDbTables.FILE_REPO+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{fileReference.getId()});
    }

    public FileReference findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.FILE_REPO + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<FileReference>() {
            public FileReference mapRow(ResultSet resultSet, int i) throws SQLException {
                FileReference fileReference = new FileReference();
                fileReference.setId(resultSet.getInt("Id"));
                fileReference.setName(resultSet.getString("Name"));
                fileReference.setUri(resultSet.getString("URI"));
                fileReference.setTime(resultSet.getTimestamp("Time"));
                return fileReference;
            }
        });
    }
}
