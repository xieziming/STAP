package com.xieziming.stap.dao.file;

import com.xieziming.stap.core.file.StapFile;
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
public class StapFileDao {

    public void add(StapFile stapFile) {
        String sql = "INSERT INTO "+StapDbTables.FILE.toString()+" SET Name=?, URI=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile.getName(), stapFile.getUri()});
    }

    public void update(StapFile stapFile) {
        String sql = "UPDATE "+StapDbTables.FILE.toString()+" SET Name=?, URI=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile.getName(), stapFile.getUri(), stapFile.getId()});
    }

    public void delete(StapFile stapFile) {
        String sql = "DELETE FROM "+StapDbTables.FILE.toString()+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile.getId()});
    }

    public StapFile findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.FILE.toString() + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<StapFile>() {
            public StapFile mapRow(ResultSet resultSet, int i) throws SQLException {
                StapFile stapFile = new StapFile();
                stapFile.setId(resultSet.getInt("Id"));
                stapFile.setName(resultSet.getString("Name"));
                stapFile.setUri(resultSet.getString("URI"));
                stapFile.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                return stapFile;
            }
        });
    }
}
