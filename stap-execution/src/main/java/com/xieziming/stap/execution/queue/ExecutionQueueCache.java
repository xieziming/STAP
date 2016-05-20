package com.xieziming.stap.execution.queue;

import com.xieziming.stap.core.execution.Execution;
import com.xieziming.stap.core.execution.ExecutionPlan;
import com.xieziming.stap.core.testcase.TestCase;
import com.xieziming.stap.db.StapDbUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Suny on 5/20/16.
 */
public class ExecutionQueueCache {
    private static Queue<Execution> executionQueue = new LinkedList<Execution>();
    public static Queue<Execution> getExecutionQueue(){
        if(executionQueue == null || executionQueue.size() == 0){
            synchronized (ExecutionQueueCache.class){
                if(executionQueue == null || executionQueue.size() == 0){
                    List<Execution> executionList = getExecutionList();
                    for (Execution execution : executionList){
                        executionQueue.offer(execution);
                    }
                }
            }
        }
        return executionQueue;
    }

    public static List<Execution> getExecutionList(){
        String sql = "SELECT p.id AS Plan_Id, p.name AS Plan_Name, t.Id AS Test_Case_Id, t.Name AS Test_Case_Name, e.id AS Execution_Id, e.Start_Time, e.End_Time, e.Status, e.Result, e.Remark FROM Execution e LEFT JOIN execution_plan p ON e.Execution_Plan_Id = p.Id LEFT JOIN test_case t ON t.id = e.Test_Case_Id";
        return StapDbUtil.getJdbcTemplate().query(sql, new RowMapper<Execution>() {
            @Override
            public Execution mapRow(ResultSet resultSet, int i) throws SQLException {
                Execution execution = new Execution();
                execution.setId(resultSet.getInt("Execution_Id"));
                execution.setStatus(resultSet.getString("Status"));
                execution.setStartTime(resultSet.getTimestamp("Start_Time"));
                execution.setEndTime(resultSet.getTimestamp("End_Time"));
                execution.setResult(resultSet.getString("Result"));
                execution.setRemark(resultSet.getString("Remark"));

                TestCase testCase = new TestCase();
                testCase.setId(resultSet.getInt("Test_Case_Id"));
                testCase.setName(resultSet.getString("Test_Case_Name"));
                execution.setTestCase(testCase);

                ExecutionPlan executionPlan = new ExecutionPlan();
                executionPlan.setId(resultSet.getInt("Plan_Id"));
                executionPlan.setName(resultSet.getString("Plan_Name"));
                execution.setExecutionPlan(executionPlan);

                return execution;
            }
        });
    }
}
