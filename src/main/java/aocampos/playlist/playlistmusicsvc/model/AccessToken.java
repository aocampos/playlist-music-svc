package aocampos.playlist.playlistmusicsvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessToken {

    private String access_token;
    private String token_type;
    private String expires_in;
}
