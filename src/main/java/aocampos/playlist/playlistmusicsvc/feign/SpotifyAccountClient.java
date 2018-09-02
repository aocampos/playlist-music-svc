package aocampos.playlist.playlistmusicsvc.feign;

import aocampos.playlist.playlistmusicsvc.model.AccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "spotify", url = "${spotify.url.account}")
public interface SpotifyAccountClient {

    @PostMapping(value = "/token",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<AccessToken> generateAccessToken(@RequestHeader("Authorization") String authorization,
                                                    MultiValueMap<String, String> grantType);
}
