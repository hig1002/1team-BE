package kwthon_1team.kwthon.domian.dto.response;


import kwthon_1team.kwthon.domian.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MailDetailResponse {
    private Long mailId;
    private String mainTitle;
    private String mailText;
    private Boolean isPublic;
    private Long senderId;
    private String senderName;
    private LocalDateTime mailDate;
    private List<Photo> photos;
}
