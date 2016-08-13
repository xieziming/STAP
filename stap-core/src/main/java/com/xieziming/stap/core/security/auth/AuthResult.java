package com.xieziming.stap.core.security.auth;

import com.xieziming.stap.core.model.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/6/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResult {
    private boolean authSuccess;
    private String authFailureReason;
    private String token;
    private UserDto userDto;
}
