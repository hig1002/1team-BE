package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MailResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class myMailListResultDto{
        private List<MailListDataDto> mails;
    }

}
