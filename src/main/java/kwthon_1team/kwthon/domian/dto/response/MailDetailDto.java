package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;
import java.util.List;

@Data
public class MailDetailDto {
    private String mailName;
    private String mailText;
    private Long receiverId;
    private String receiverName;
    private Long senderId;
    private String senderName;
    private boolean isisPublic;
    private List<PhotoViewDto> photos;

    public MailDetailDto(String mailName, String mailText, Long receiverId, String receiverName,
                         Long senderId, String senderName, boolean isisPublic, List<PhotoViewDto> photos) {
        this.mailName = mailName;
        this.mailText = mailText;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.senderId = senderId;
        this.senderName = senderName;
        this.isisPublic = isisPublic;
        this.photos = photos;
    }

}