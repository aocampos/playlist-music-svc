package aocampos.playlist.playlistmusicsvc.feign;

import aocampos.playlist.playlistmusicsvc.model.Recommendation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spotify", url = "${spotify.url.api}")
public interface SpotifyApiClient {

    @GetMapping(value = "/recommendations")
    ResponseEntity<Recommendation> getTrackRecomendation(@RequestHeader("Authorization") String authorization,
                                                         @RequestParam("limit") int trackLimitResult,
                                                         @RequestParam("seed_genres") String genre);

}
