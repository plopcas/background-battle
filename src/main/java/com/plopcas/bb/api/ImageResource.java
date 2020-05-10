package com.plopcas.bb.api;

import com.plopcas.bb.configuration.HttpSessionConfig;
import com.plopcas.bb.model.Image;
import com.plopcas.bb.model.Player;
import com.plopcas.bb.model.Tournament;
import com.plopcas.bb.service.ImageService;
import com.plopcas.bb.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageResource {
    private static Logger log = LoggerFactory.getLogger(ImageResource.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpSessionConfig sessionConfig;

    @Autowired
    private TournamentService tournamentService;

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> postImage(@RequestParam(value = "image") MultipartFile image,
                                       @RequestParam(value = "username") String username,
                                       @RequestParam(value = "tournamentId") String tournamentId) {
        log.info("Post image");
        Image uploadedImage = imageService.uploadImage(image);

        // could get it from active sessions instead of searching by tournament ID
        // List<HttpSession> activeSessions = sessionConfig.getActiveSessions();

        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        Player player = tournament.getPlayers().get(username);
        player.getImages().add(uploadedImage);

        return ResponseEntity.ok().build();
    }
}
