package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.request.UploadLetterRequestDto;
import kwthon_1team.kwthon.domian.dto.response.UploadLetterResponseDto;
import kwthon_1team.kwthon.service.MailService;
import kwthon_1team.kwthon.service.UploadLetterService;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import java.util.List;

@RestController
@RequestMapping("mail")
@RequiredArgsConstructor
public class UploadLetterController {
    private final UploadLetterService uploadLetterService;

    @PostMapping(value = "/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse<UploadLetterResponseDto> uploadLetter(
            @PathVariable("memberId") Long memberId,
            @RequestPart("request") UploadLetterRequestDto request,
            @RequestPart(value = "mailPhoto", required = false) List<MultipartFile> mailPhotos
    ){
        try {
            return new BaseResponse(HttpStatus.OK.value(), "편지 작성이 완료되었습니다.", uploadLetterService.registerLetter(memberId, request, mailPhotos));
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }


}
