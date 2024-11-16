package kwthon_1team.kwthon.domian.dto.response;

import kwthon_1team.kwthon.domian.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class MailSummaryResponse {
    private Long mailId;
    private LocalDateTime mailDate;
    private String mailTitle;
    private Long senderId;
    private String senderName;
    private Boolean isPublic;
    private Photo photo;
}
