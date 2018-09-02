package aocampos.playlist.playlistmusicsvc.service;

import aocampos.playlist.playlistmusicsvc.feign.SpotifyAccountClient;
import aocampos.playlist.playlistmusicsvc.feign.SpotifyApiClient;
import aocampos.playlist.playlistmusicsvc.model.AccessToken;
import aocampos.playlist.playlistmusicsvc.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class TrackRecommendationService {

    @Value("${spotify.limit}")
    private int trackLimitResult;

    private final SpotifyApiClient spotifyApiClient;
    private final SpotifyAccountClient spotifyAccountClient;
    private final AuthorizationService authorizationService;

    @Autowired
    public TrackRecommendationService(SpotifyApiClient spotifyApiClient,
                                      SpotifyAccountClient spotifyAccountClient,
                                      AuthorizationService authorizationService) {
        this.spotifyApiClient = spotifyApiClient;
        this.spotifyAccountClient = spotifyAccountClient;
        this.authorizationService = authorizationService;
    }

    public Recommendation getTracks(String genre) {

        MultiValueMap<String, String> grantType = authorizationService.getGrantType();
        String authorization = authorizationService.generateAccessToken();

        ResponseEntity<AccessToken> accessTokenResponse = spotifyAccountClient
                .generateAccessToken(authorization, grantType);
        AccessToken accessToken = accessTokenResponse.getBody();

        String tokenAuthorization = authorizationService.generateTokenAuthorization(accessToken);

        ResponseEntity<Recommendation> trackRecommendation = spotifyApiClient
                .getTrackRecomendation(tokenAuthorization, trackLimitResult, genre);
        Recommendation recommendation = trackRecommendation.getBody();

        return recommendation;
    }

}
