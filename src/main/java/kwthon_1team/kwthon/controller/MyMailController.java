package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;
import kwthon_1team.kwthon.domian.dto.response.MyMailOneDetailResponse;
import kwthon_1team.kwthon.service.MailService;
import kwthon_1team.kwthon.service.MyMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myMail")
public class MyMailController {
    private final MyMailService myMailService;

    @GetMapping("/{memberId}")
    public BaseResponse<MailPagingResponse<MailSummaryResponse>> myMailByMemberId(@PathVariable("memberId") Long memberId,
                                                                                  @RequestParam(required = true) String filter, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return new BaseResponse(HttpStatus.OK.value(),"내 메일 정보를 불러왔습니다.", myMailService.myMailByMemberId(memberId,page,size,filter));
    }

    @GetMapping("/{memberId}/{mailId}")
    public BaseResponse<MyMailOneDetailResponse> myMailDetailByMemberId(@PathVariable("memberId") Long memberId, @PathVariable Long mailId){
        return new BaseResponse(HttpStatus.OK.value(), "내 매일 정보를 불러왔습니다.", myMailService.myMailDetailBymailId(memberId, mailId));
    }
}
