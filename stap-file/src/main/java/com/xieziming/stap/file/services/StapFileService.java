package com.xieziming.stap.file.services;

import com.xieziming.stap.core.common.StapFileDetail;
import com.xieziming.stap.core.common.StapGenericException;
import com.xieziming.stap.core.common.StapMeta;
import com.xieziming.stap.file.util.StapFileDbUtil;
import com.xieziming.stap.file.util.StapFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/16/16.
 */
@Controller
public class StapFileService {
    private static Logger logger = LoggerFactory.getLogger(StapFileService.class);
    private final String UTF8 = ";charset=UTF-8";
    @RequestMapping(value = "files", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<StapFileDetail> getFiles() {
        String sql = "SELECT Id, Path, Last_Update FROM File";
        return StapFileDbUtil.getJdbcTemplate().query(sql, new RowMapper<StapFileDetail>() {
            @Override
            public StapFileDetail mapRow(ResultSet resultSet, int i) throws SQLException {
                StapFileDetail stapFileDetail = new StapFileDetail();
                stapFileDetail.setPath(resultSet.getString("Path"));
                stapFileDetail.setLastUpdate(resultSet.getTimestamp("Last_Update"));
                stapFileDetail.setMetaList(getMetaList(resultSet.getInt("Id")));
                return stapFileDetail;
            }
        });
    }

    @RequestMapping(value = "file/{path}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public StapFileDetail getFile(@PathVariable("path") String path) {
        //fill path, content
        StapFileDetail stapFileDetail = getStapFileDetail(path);
        //fill file metas
        stapFileDetail.setMetaList(getMetaList(getIdByPath(path)));
        return stapFileDetail;
    }

    @RequestMapping(value = "file", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public StapFileDetail uploadFile(@RequestBody final StapFileDetail stapFileDetail) {
        Integer fileId = getIdByPath(stapFileDetail.getPath());
        if(fileId == null) {
            addFile(stapFileDetail);
        }else{
            updateFile(stapFileDetail);
        }
        return getFile(stapFileDetail.getPath());
    }

    @RequestMapping(value = "file/{path}/content", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE+UTF8)
    @ResponseBody
    public byte[] getFileContent(@PathVariable("path") String path) {
        String sql = "SELECT Content FROM File WHERE Path = ?";
        Blob blob = StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[] { path }, Blob.class);
        return StapFileUtil.blobToBytes(blob);
    }

    @RequestMapping(value = "file/{path}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseStatus(HttpStatus.OK)
    public void deleteFile(@PathVariable("path") String path) {

        //query file id
        Integer fileId = getIdByPath(path);

        //delete from file_meta table
        String sql = "DELETE FROM File_Meta WHERE File_Id = ?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{ fileId });

        //delete from file table
        sql = "DELETE FROM File WHERE Id = ?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{ fileId });
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StapGenericException> handleAllException(Exception exception) {
        logger.error("Stap File Service Exception", exception);
        return new ResponseEntity<StapGenericException>(new StapGenericException(exception),HttpStatus.BAD_REQUEST);
    }

    private Integer getIdByPath(String path){
        String sql = "SELECT ID FROM File WHERE Path = ?";
        try {
            return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{path}, Integer.class);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private List<StapMeta> getMetaList(Integer fileId){
        final List<StapMeta> stapFileDetailMetaList = new ArrayList<StapMeta>();
        String sql = "SELECT * FROM File_Meta WHERE File_Id = ?";
        StapFileDbUtil.getJdbcTemplate().query(sql,
                new Object[]{ fileId },
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        StapMeta stapMeta  = new StapMeta();
                        stapMeta.setMetaKey(rs.getString("Meta_Key"));
                        stapMeta.setMetaValue(rs.getString("Meta_Value"));
                        stapMeta.setLastUpdate(rs.getTimestamp("Last_Update"));
                        stapFileDetailMetaList.add(stapMeta);
                    }
                });
        return stapFileDetailMetaList;
    }

    private StapFileDetail getStapFileDetail(String path){
        String sql = "SELECT * FROM File WHERE Path = ?";
        return StapFileDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{ path }, new StapFileDetailRowMapper());
    }

    private void updateFile(final StapFileDetail stapFileDetail) {
        final Integer fileId = getIdByPath(stapFileDetail.getPath());
        String sql = "UPDATE File SET Content=? WHERE Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFileDetail.getContent(), fileId});

        sql = "DELETE FROM File_Meta WHERE File_Id=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{fileId});

        addMetas(fileId, stapFileDetail.getMetaList());
    }

    private void addFile(final StapFileDetail stapFileDetail) {
        String sql = "INSERT INTO File SET Path=?, Content=?";
        StapFileDbUtil.getJdbcTemplate().update(sql, new Object[]{stapFileDetail.getPath(), stapFileDetail.getContent()});

        addMetas(getIdByPath(stapFileDetail.getPath()), stapFileDetail.getMetaList());
    }

    private void addMetas(final Integer fileId, final List<StapMeta> metaList){
        String sql = "INSERT INTO File_Meta SET File_Id=?, Meta_Key=?, Meta_Value=?";
        StapFileDbUtil.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, fileId);
                preparedStatement.setString(2, metaList.get(i).getMetaKey());
                preparedStatement.setString(3, metaList.get(i).getMetaValue());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

    class StapFileDetailRowMapper implements RowMapper<StapFileDetail> {

        public StapFileDetail mapRow(ResultSet resultSet, int i) throws SQLException {
            StapFileDetail stapFileDetail = new StapFileDetail();
            stapFileDetail.setId(resultSet.getInt("Id"));
            stapFileDetail.setPath(resultSet.getString("Path"));
            stapFileDetail.setContent(resultSet.getBytes("Content"));
            stapFileDetail.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return stapFileDetail;
        }
    }
}

