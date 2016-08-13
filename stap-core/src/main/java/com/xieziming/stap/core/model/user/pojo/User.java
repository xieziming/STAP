package com.xieziming.stap.core.model.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 8/13/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private Integer userRoleId;
    private byte[] avatar;
}
