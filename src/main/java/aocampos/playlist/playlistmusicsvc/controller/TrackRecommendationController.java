package aocampos.playlist.playlistmusicsvc.controller;

import aocampos.playlist.playlistmusicsvc.model.Recommendation;
import aocampos.playlist.playlistmusicsvc.service.TrackRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackRecommendationController {

    private final TrackRecommendationService trackRecommendationService;

    @Autowired
    public TrackRecommendationController(TrackRecommendationService trackRecommendationService) {
        this.trackRecommendationService = trackRecommendationService;
    }

    @GetMapping("/genre/{genre}")
    public Recommendation getTracks(@PathVariable("genre") String genre) {
        return trackRecommendationService.getTracks(genre);
    }
}
