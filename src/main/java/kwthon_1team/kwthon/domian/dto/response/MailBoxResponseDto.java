package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MailBoxResponseDto {
    private int status;
    private String message;
    private MailBoxDataDto data;

    @Getter
    @AllArgsConstructor
    public static class MailBoxDataDto {
        private List<MailDto> mails;
    }
}
