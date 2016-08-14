package com.xieziming.stap.core.model.file.dao;

import com.xieziming.stap.core.model.file.pojo.FileMeta;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/21/16.
 */
@Component
public class FileMetaDao {

    public void add(FileMeta fileMeta) {
        String sql = "INSERT INTO "+StapDbTables.FILE_META +" SET File_Id=?, Meta_Key=?, Meta_Value=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{ fileMeta.getFileId(), fileMeta.getMetaKey(), fileMeta.getMetaValue()});
    }

    public void update(FileMeta fileMeta) {
        String sql = "UPDATE "+StapDbTables.FILE_META +" SET File_Id=?, Meta_Key=?, Meta_Value=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{ fileMeta.getFileId(), fileMeta.getMetaKey(), fileMeta.getMetaValue(), fileMeta.getId()});
    }

    public void delete(FileMeta fileMeta) {
        String sql = "DELETE FROM "+StapDbTables.FILE_META +" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{fileMeta.getId()});
    }

    public FileMeta findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.FILE_META + " WHERE Id=?";
        return StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, fileMetaRowMapper);
    }

    public List<FileMeta> findAllByFileId(int fileId) {
        String sql = "SELECT * FROM " + StapDbTables.FILE_META + " WHERE File_Id=?";
        return StapDbUtil.getJdbcTemplate().query(sql, new Object[]{fileId}, fileMetaRowMapper);
    }

    RowMapper<FileMeta> fileMetaRowMapper = new RowMapper<FileMeta>() {
        public FileMeta mapRow(ResultSet resultSet, int i) throws SQLException {
            FileMeta fileMeta = new FileMeta();
            fileMeta.setFileId(resultSet.getInt("File_Id"));
            fileMeta.setMetaKey(resultSet.getString("Meta_Key"));
            fileMeta.setMetaValue(resultSet.getString("Meta_Value"));
            fileMeta.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return fileMeta;
        }
    };
}
