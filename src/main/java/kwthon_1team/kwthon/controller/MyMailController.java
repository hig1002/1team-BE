package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.request.MailRequestDto;
import kwthon_1team.kwthon.domian.dto.response.MailResponseDto;
import kwthon_1team.kwthon.service.MyMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myMail")
public class MyMailController {
    private final MyMailService myMailService;

    @GetMapping("/{memberId}")
    public ResponseEntity<BaseResponse<MailResponseDto.myMailListResultDto>> mailList(@PathVariable Long memberId, @RequestParam(required = true) String filter){
        //여기 채워야함
        MailResponseDto.myMailListResultDto mails;

        BaseResponse<MailResponseDto.myMailListResultDto> response =
                new BaseResponse<>(200,"내 메일 정보를 불러왔습니다.", mails);

        return ResponseEntity.ok(response);
    }


}
