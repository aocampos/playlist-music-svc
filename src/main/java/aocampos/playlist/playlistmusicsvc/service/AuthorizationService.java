package aocampos.playlist.playlistmusicsvc.service;

import aocampos.playlist.playlistmusicsvc.model.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;

@Service
public class AuthorizationService {

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    @Value(("${spotify.credentials}"))
    private String credentials;

    public String generateAccessToken() {
        String clientAuthorization = clientId + ":" + clientSecret;
        String authorizationEncode = Base64.getEncoder().encodeToString(clientAuthorization.getBytes());
        String authorization = "Basic " + authorizationEncode;

        return authorization;
    }

    public String generateTokenAuthorization(AccessToken accessToken) {
        String tokenAuthorization = accessToken.getToken_type() + ' ' + accessToken.getAccess_token();
        return tokenAuthorization;
    }

    public MultiValueMap<String, String> getGrantType() {
        MultiValueMap<String, String> grantType = new LinkedMultiValueMap<>();
        grantType.set("grant_type", credentials);
        return grantType;
    }
}
