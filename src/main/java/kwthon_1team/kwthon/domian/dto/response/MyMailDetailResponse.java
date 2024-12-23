package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyMailDetailResponse {
    private Long mailId;
    private LocalDateTime mailDate;
    private String mailTitle;
    private Long senderId;
    private String senderName;
    private Boolean isPublic;
    private List<PhotoDto> photos;
}