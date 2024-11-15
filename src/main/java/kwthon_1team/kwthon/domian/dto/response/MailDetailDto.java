package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;
import java.util.List;

@Data
public class MailDetailDto {
    private String mailName;
    private String mailText;
    private String receiverId;
    private String receiverName;
    private String senderId;
    private String senderName;
    private boolean isPublic;
    private List<PhotoDto> photos;

    public MailDetailDto(String mailName, String mailText, String receiverId, String receiverName,
                         String senderId, String senderName, boolean isPublic, List<PhotoDto> photos) {
        this.mailName = mailName;
        this.mailText = mailText;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.senderId = senderId;
        this.senderName = senderName;
        this.isPublic = isPublic;
        this.photos = photos;
    }

}