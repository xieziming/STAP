package com.xieziming.stap.core.execution.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionPlan {
    private Integer id;
    private String name;
    private String description;
    private String status;
}
