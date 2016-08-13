package com.xieziming.stap.core.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Suny on 7/11/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialCache {
    private String name;
    private String token;
}
