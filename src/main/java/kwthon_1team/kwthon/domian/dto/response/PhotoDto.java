package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;

@Data
@Getter
public class PhotoDto {
    private Long photoId;
    private String photoUrl;

    public PhotoDto(Long photoId, String photoUrl) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;

    }
}
