package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyMailOneDetailResponse {
    private Long mailId;
    private String mailTitle;
    private String mailText;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private List<PhotoDto> photos;
}
