package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;

@Data
public class MailDto {
    private String mailDate;
    private String mailName;
    private Long senderId;
    private String name;

    public MailDto(String mailDate, String mailName, Long senderId, String name) {
        this.mailDate = mailDate;
        this.mailName = mailName;
        this.senderId = senderId;
        this.name = name;
    }

}
