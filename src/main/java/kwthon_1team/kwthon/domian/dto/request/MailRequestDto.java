package kwthon_1team.kwthon.domian.dto.request;

import lombok.Getter;

public class MailRequestDto {
    @Getter
    public static class myMailListDto {
        Long memberId;
        String filter;
    }
}
