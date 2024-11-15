package kwthon_1team.kwthon.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVerificationEmailRequestDto {
    private Integer code;
}
