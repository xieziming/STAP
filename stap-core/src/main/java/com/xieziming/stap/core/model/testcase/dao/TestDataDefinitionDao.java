package com.xieziming.stap.core.model.testcase.dao;

import com.xieziming.stap.core.constants.TestDataType;
import com.xieziming.stap.core.model.testcase.pojo.TestDataDefinition;
import com.xieziming.stap.db.StapDbTables;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/10/16.
 */
@Component
public class TestDataDefinitionDao {
    @Autowired
    private TestDataDefinitionRevisionDao testDataDefinitionRevisionDao;

    public void add(TestDataDefinition testDataDefinition) {
        String sql = "INSERT INTO "+StapDbTables.TEST_DATA_DEFINITION+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinition.getField(), testDataDefinition.getValue(), testDataDefinition.getRemark(), testDataDefinition.getType(), testDataDefinition.getFileId()});
    }

    public void update(TestDataDefinition testDataDefinition) {
        String sql = "UPDATE "+StapDbTables.TEST_DATA_DEFINITION+" SET Field=?, Value=?, Remark=?, Type=?, File_Id=? WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{testDataDefinition.getField(), testDataDefinition.getValue(), testDataDefinition.getRemark(), testDataDefinition.getType(), testDataDefinition.getFileId(), testDataDefinition.getId()});
    }

    public boolean canDelete(Integer id){
        String sql = "SELECT COUNT(ID) FROM "+StapDbTables.TEST_DATA_DEFINITION+" WHERE Type != ? AND Id=?";
        int recordNo = StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{TestDataType.GLOBAL, id}, Integer.class);
        if(recordNo > 0) return true;
        return false;
    }

    public void deleteLocalById(Integer id){
        if(canDelete(id)) delete(id);
    }

    public void deleteGlobalById(int id){
        delete(id);
    }

    private void delete(Integer id){
        testDataDefinitionRevisionDao.deleteAll(id);
        String sql = "DELETE FROM "+StapDbTables.TEST_DATA_DEFINITION+" WHERE Id=?";
        StapDbUtil.getJdbcTemplate().update(sql, new Object[]{id});
    }

    public List<TestDataDefinition> findAll(){
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA_DEFINITION;
        return  StapDbUtil.getJdbcTemplate().query(sql, new Object[0], testDataDefinitionRowMapper);
    }

    public TestDataDefinition findById(int id) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA_DEFINITION + " WHERE Id=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{id}, testDataDefinitionRowMapper);
    }

    public TestDataDefinition findByField(String field) {
        String sql = "SELECT * FROM " + StapDbTables.TEST_DATA_DEFINITION + " WHERE Field=?";
        return  StapDbUtil.getJdbcTemplate().queryForObject(sql, new Object[]{field}, testDataDefinitionRowMapper);
    }

    RowMapper<TestDataDefinition> testDataDefinitionRowMapper = new RowMapper<TestDataDefinition>() {
        public TestDataDefinition mapRow(ResultSet resultSet, int i) throws SQLException {
            TestDataDefinition testDataDefinition = new TestDataDefinition();
            testDataDefinition.setId(resultSet.getInt("Id"));
            testDataDefinition.setField(resultSet.getString("Field"));
            testDataDefinition.setValue(resultSet.getString("Value"));
            testDataDefinition.setRemark(resultSet.getString("Remark"));
            testDataDefinition.setType(resultSet.getString("Type"));
            testDataDefinition.setFileId(resultSet.getInt("File_Id"));
            testDataDefinition.setLastUpdate(resultSet.getTimestamp("Last_Update"));
            return testDataDefinition;
        }
    };
}
