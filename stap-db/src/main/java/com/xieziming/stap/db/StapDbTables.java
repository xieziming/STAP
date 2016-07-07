package com.xieziming.stap.db;

/**
 * Created by Suny on 5/21/16.
 */
public enum StapDbTables {
    TEST_CASE("Test_Case"),
    TEST_CASE_META("Test_Case_Meta"),
    TEST_CASE_DEPENDENCE("Test_Case_Dependence"),
    TEST_DATA("Test_Data"),
    TEST_DATA_DEFINITION("Test_Data_Definition"),
    TEST_STEP("Test_Step"),
    TEST_ACTION("Test_Action"),

    EXECUTION("Execution"),
    EXECUTION_CONTEXT("Execution_Context"),
    EXECUTION_LOG("Execution_Log"),

    EXECUTION_PLAN("Execution_Plan"),
    EXECUTION_PLAN_META("Execution_Plan_Meta"),
    EXECUTION_PLAN_LOG("Execution_Plan_Log"),

    EXECUTION_STEP("Execution_Step"),
    EXECUTION_STEP_LOG("Execution_Step_Log"),
    EXECUTION_STEP_OUTPUT_TEXT("Execution_Step_Output_Text"),
    EXECUTION_STEP_OUTPUT_FILE("Execution_Step_Output_File"),

    FILE("File"),
    LOG("Log"),

    STAP_USER("stap_user"),
    STAP_USER_ROLE("stap_user_role");

    private String tableName;

    StapDbTables(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return tableName;
    }
}
