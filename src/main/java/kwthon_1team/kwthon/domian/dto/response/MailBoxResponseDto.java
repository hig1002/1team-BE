package kwthon_1team.kwthon.domian.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailBoxResponseDto {
    private int status;  // 상태 코드 (200, 404 등)
    private String message;  // 응답 메시지
    private MailDetailDto data;  // 메일 상세 데이터 (MailDetailDto 객체)
}
