package com.xieziming.stap.core.model.file.dao;

import com.xieziming.stap.core.model.file.pojo.File;
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
public class FileDao {

    public void add(File file) {
        String sql = "INSERT INTO "+StapDbTables.FILE +" SET Name=?, Url=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{file.getName(), file.getUrl()});
    }

    public void update(File file) {
        String sql = "UPDATE "+StapDbTables.FILE +" SET Name=?, Url=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{file.getName(), file.getUrl(), file.getId()});
    }

    public void delete(File file) {
        String sql = "DELETE FROM "+StapDbTables.FILE +" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{file.getId()});
    }

    public File findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.FILE + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<File>() {
            public File mapRow(ResultSet resultSet, int i) throws SQLException {
                File file = new File();
                file.setId(resultSet.getInt("Id"));
                file.setName(resultSet.getString("Name"));
                file.setUrl(resultSet.getString("Url"));
                file.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return file;
            }
        });
    }
}
