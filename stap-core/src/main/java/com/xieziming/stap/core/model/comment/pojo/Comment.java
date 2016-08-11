package com.xieziming.stap.core.model.comment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Suny on 8/12/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer parentCommentId;
    private Integer testCaseId;
    private Integer executionPlanId;
    private Integer executionId;
    private String content;
    private Integer userId;
    private Date time;
}
