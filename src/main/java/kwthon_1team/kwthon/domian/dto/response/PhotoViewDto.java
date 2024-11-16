package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;

@Data
@Getter
public class PhotoViewDto {
    private Long photoId;
    private String photoUrl;

    public PhotoViewDto(Long photoId, String photoUrl) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;

    }
}
