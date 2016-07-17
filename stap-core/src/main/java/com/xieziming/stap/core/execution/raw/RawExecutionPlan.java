package com.xieziming.stap.core.execution.raw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawExecutionPlan {
    private Integer id;
    private String name;
    private String remark;
    private String status;
}
