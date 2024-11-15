package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PhotoDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhotoData{
        private Long photoId;
        private String photoUrl;
    }
}
