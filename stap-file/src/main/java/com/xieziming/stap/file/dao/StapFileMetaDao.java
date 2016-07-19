package com.xieziming.stap.file.dao;

import com.xieziming.stap.core.model.file.pojo.StapFileMeta;
import com.xieziming.stap.file.util.StapFileDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 7/19/16.
 */
@Component
public class StapFileMetaDao {
    private static final String STAP_FILE_META_TABLE = "Stap_File_Meta";

    public List<StapFileMeta> findAll(int fileId) {
        String sql = "SELECT * FROM "+STAP_FILE_META_TABLE+" WHERE File_Id=?";
        return StapFileDbUtil.getJdbcTemplate().query(sql, new Object[]{fileId}, stapFileMetaRowMapper);
    }

    public StapFileMeta find(String id) {
        String sql = "SELECT * FROM "+STAP_FILE_META_TABLE+" WHERE Id=?";
        return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, stapFileMetaRowMapper);
    }

    public void delete(int id) {
        String sql = "DELETE FROM "+STAP_FILE_META_TABLE+" WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public void deleteAllByFileId(int fileId){
        String sql = "DELETE FROM "+STAP_FILE_META_TABLE+" WHERE File_Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{fileId});
    }

    public void put(StapFileMeta stapFileMeta){
        if(stapFileMeta.getId() != null && stapFileMeta.getId() > 0){
            update(stapFileMeta);
        }else{
            add(stapFileMeta);
        }
    }

    public void put(List<StapFileMeta> stapFileMetaList){
        for(StapFileMeta stapFileMeta : stapFileMetaList){
            put(stapFileMeta);
        }
    }

    private void add(StapFileMeta stapFileMeta) {
        String sql = "INSERT INTO "+STAP_FILE_META_TABLE+" SET File_Id=?, Meta_key=?, Meta_Value=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFileMeta.getFileId(), stapFileMeta.getMetaKey(), stapFileMeta.getMetaValue()});
    }

    private void update(StapFileMeta stapFileMeta) {
        String sql = "UPDATE "+STAP_FILE_META_TABLE+" SET File_Id=?, Meta_key=?, Meta_Value=? WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFileMeta.getFileId(), stapFileMeta.getMetaKey(), stapFileMeta.getMetaValue(), stapFileMeta.getId()});
    }

    private RowMapper<StapFileMeta> stapFileMetaRowMapper = new RowMapper<StapFileMeta>() {
        public StapFileMeta mapRow(ResultSet resultSet, int i) throws SQLException {
            StapFileMeta stapFileMeta = new StapFileMeta();
            stapFileMeta.setId(resultSet.getInt("Id"));
            stapFileMeta.setFileId(resultSet.getInt("File_Id"));
            stapFileMeta.setMetaKey(resultSet.getString("Meta_Key"));
            stapFileMeta.setMetaValue(resultSet.getString("Meta_Value"));
            stapFileMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return stapFileMeta;
        }
    };
}
