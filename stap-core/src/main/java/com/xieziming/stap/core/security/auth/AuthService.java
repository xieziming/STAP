package com.xieziming.stap.core.security.auth;

/**
 * Created by Suny on 7/6/16.
 */
public interface AuthService {
    AuthResult auth(Credential credential);
    AuthResult hasAuth(CredentialCache credentialCache);
    void cacheAuth(AuthResult authResult);
    void invalidateAuthCache(CredentialCache credentialCache);
}
