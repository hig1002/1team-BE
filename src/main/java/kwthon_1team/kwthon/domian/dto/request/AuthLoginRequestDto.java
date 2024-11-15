package kwthon_1team.kwthon.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDto {
    private Long studentId;
    private String password;
}
