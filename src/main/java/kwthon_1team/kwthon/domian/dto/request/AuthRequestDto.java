package kwthon_1team.kwthon.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private Long studentId;
    private String department;
    private String name;
    private String email;
    private String passwords;
    private Boolean agreement;
}
