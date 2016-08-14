package com.xieziming.stap.file.dao;

import com.xieziming.stap.core.model.file.pojo.StapFile2;
import com.xieziming.stap.file.util.StapFileDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 7/19/16.
 */
@Component
public class StapFileDao {
    private static final String STAP_FILE_TABLE = "Stap_File";
    @Autowired
    private StapFileMetaDao stapFileMetaDao;

    public List<StapFile2> findAll() {
        String sql = "SELECT * FROM "+STAP_FILE_TABLE;
        return StapFileDbUtil.getJdbcTemplate().query(sql, new Object[0], stapFileRowMapper);
    }

    public StapFile2 find(String id) {
        String sql = "SELECT * FROM "+STAP_FILE_TABLE+" WHERE Id=?";
        return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, stapFileRowMapper);
    }

    public StapFile2 findByPath(String path){
        String sql = "SELECT * FROM "+STAP_FILE_TABLE+" WHERE Path = ?";
        return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{ path }, stapFileRowMapper);
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+STAP_FILE_TABLE+" WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteByPath(String path){
        String sql = "DELETE FROM "+STAP_FILE_TABLE+" WHERE Path=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{path});
    }

    public void put(StapFile2 stapFile2){
        if(stapFile2.getId() != null && stapFile2.getId() > 0){
            update(stapFile2);
        }else{
            add(stapFile2);
        }
    }

    private void add(StapFile2 stapFile2) {
        String sql = "INSERT INTO "+STAP_FILE_TABLE+" SET Path=?, Content=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile2.getPath(), stapFile2.getContent()});
    }

    private void update(StapFile2 stapFile2) {
        String sql = "UPDATE "+STAP_FILE_TABLE+" SET Path=?, Content=? WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile2.getPath(), stapFile2.getContent(), stapFile2.getId()});
    }

    private RowMapper<StapFile2> stapFileRowMapper = new RowMapper<StapFile2>() {
        public StapFile2 mapRow(ResultSet resultSet, int i) throws SQLException {
            StapFile2 stapFile2 = new StapFile2();
            stapFile2.setId(resultSet.getInt("Id"));
            stapFile2.setPath(resultSet.getString("Path"));
            stapFile2.setContent(resultSet.getBytes("Content"));
            stapFile2.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return stapFile2;
        }
    };
}
