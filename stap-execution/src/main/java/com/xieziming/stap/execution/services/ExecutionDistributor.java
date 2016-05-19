package com.xieziming.stap.execution.services;

import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.db.StapDbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Suny on 5/19/16.
 */
@Controller
public class ExecutionDistributor {
    private static Logger logger = LoggerFactory.getLogger(ExecutionDistributor.class);
    private final String UTF8 = ";charset=UTF-8";
    @RequestMapping(value = "executions", method = RequestMethod.GET, produces = PageAttributes.MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<Execution> getExecutions() {
        String sql = "SELECT p.id AS Plan_Id, p.name AS Plan_Name, t.Id AS Test_Case_Id, t.Name AS Test_Case_Name, e.id AS Execution_Id, e.Start_Time, e.End_Time, e.Status, e.Result, e.Remark FROM Execution e LEFT JOIN execution_plan p ON e.Execution_Plan_Id = p.Id LEFT JOIN test_case t ON t.id = e.Test_Case_Id";
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<Execution>() {
            @Override
            public Execution mapRow(ResultSet resultSet, int i) throws SQLException {
                Execution execution = new Execution();
                execution.setId(resultSet.getInt("Execution_Id"));
                execution.setStatus(resultSet.getString("Status"));
                execution.setStartTime(resultSet.getTimestamp("Start_Time"));
                execution.setEndTime(resultSet.getTimestamp("End_Time"));
                execution.setTestCaseId(resultSet.getInt("Test_Case_Id"));
                execution.setResult(resultSet.getString("Result"));
                return stapFileDetail;
            }
        });
    }
}
