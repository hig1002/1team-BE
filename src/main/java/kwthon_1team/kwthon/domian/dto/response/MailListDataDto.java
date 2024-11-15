package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MailListDataDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MailDataDto{
        private LocalDate mailDate;
        private String mailName;
        private Long senderId;
        private String name;
        private List<PhotoDto> photos;
    }
}
