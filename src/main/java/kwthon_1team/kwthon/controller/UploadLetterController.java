package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.request.UploadLetterRequestDto;
import kwthon_1team.kwthon.domian.dto.response.UploadLetterResponseDto;
import kwthon_1team.kwthon.service.MailService;
import kwthon_1team.kwthon.service.UploadLetterService;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class UploadLetterController {
    private final UploadLetterService uploadLetterService;

    @PostMapping("/{memberId}")
    public BaseResponse<UploadLetterResponseDto> uploadLetter(@PathVariable("memberId") Long memberId, @ModelAttribute UploadLetterRequestDto request){
        try {
            System.out.println("getPhotos" + request.getPhotos());
            return new BaseResponse(HttpStatus.OK.value(), "편지 작성이 완료되었습니다.", uploadLetterService.registerLetter(memberId, request));
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }


}
