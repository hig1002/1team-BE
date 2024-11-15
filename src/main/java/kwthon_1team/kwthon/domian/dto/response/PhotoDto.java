package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;

@Data
public class PhotoDto {
    private Long photoId;
    private String photoUrl;
    private String error;

    public PhotoDto(Long photoId, String photoUrl, String error) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.error = error;
    }

    public PhotoDto(Long photoId, String photoUrl) {
        this(photoId, photoUrl, null);
    }

}
