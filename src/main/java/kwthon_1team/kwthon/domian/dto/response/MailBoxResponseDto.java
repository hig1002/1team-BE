package kwthon_1team.kwthon.domian.dto.response;

import lombok.*;
import java.util.List;

@Data
public class MailBoxResponseDto {
    private int status;
    private String message;
    private MailBoxDataDto data;

    public MailBoxResponseDto(int status, String message, MailBoxDataDto data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}

class MailBoxDataDto {
    private List<MailDto> mails;

    public MailBoxDataDto(List<MailDto> mails) {
        this.mails = mails;
    }

}