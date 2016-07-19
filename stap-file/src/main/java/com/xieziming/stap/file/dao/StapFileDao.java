package com.xieziming.stap.file.dao;

import com.xieziming.stap.core.model.file.pojo.StapFile;
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

    public List<StapFile> findAll() {
        String sql = "SELECT * FROM "+STAP_FILE_TABLE;
        return StapFileDbUtil.getJdbcTemplate().query(sql, new Object[0], stapFileRowMapper);
    }

    public StapFile find(String id) {
        String sql = "SELECT * FROM "+STAP_FILE_TABLE+" WHERE Id=?";
        return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, stapFileRowMapper);
    }

    public StapFile findByPath(String path){
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

    public void put(StapFile stapFile){
        if(stapFile.getId() != null && stapFile.getId() > 0){
            update(stapFile);
        }else{
            add(stapFile);
        }
    }

    private void add(StapFile stapFile) {
        String sql = "INSERT INTO "+STAP_FILE_TABLE+" SET Path=?, Content=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile.getPath(), stapFile.getContent()});
    }

    private void update(StapFile stapFile) {
        String sql = "UPDATE "+STAP_FILE_TABLE+" SET Path=?, Content=? WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFile.getPath(), stapFile.getContent(), stapFile.getId()});
    }

    private RowMapper<StapFile> stapFileRowMapper = new RowMapper<StapFile>() {
        public StapFile mapRow(ResultSet resultSet, int i) throws SQLException {
            StapFile stapFile= new StapFile();
            stapFile.setId(resultSet.getInt("Id"));
            stapFile.setPath(resultSet.getString("Path"));
            stapFile.setContent(resultSet.getBytes("Content"));
            stapFile.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return stapFile;
        }
    };
}
