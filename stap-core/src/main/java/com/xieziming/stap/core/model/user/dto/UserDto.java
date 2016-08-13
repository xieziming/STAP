package com.xieziming.stap.core.model.user.dto;

import com.xieziming.stap.core.model.user.pojo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 8/13/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String nickName;
    private String email;
    private UserRole userRole;
    private byte[] avatar;
}
