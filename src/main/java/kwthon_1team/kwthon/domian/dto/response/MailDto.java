package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailDto {
    private String mailDate;
    private String mailName;
    private Long senderId;
    private String name; // 송신자 이름
    private Long photoId;
    private String photoUrl;
}