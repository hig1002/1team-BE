package kwthon_1team.kwthon.domian.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadLetterRequestDto {
    //private Long memberId;
    private Long receiverId;
    private String mailTitle;
    private String mailText;
    private Boolean isPublic;
}
