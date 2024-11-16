package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.response.MailDetailResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;
import kwthon_1team.kwthon.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/friendMail/{memberId}")
    public BaseResponse<MailPagingResponse<MailSummaryResponse>> inquiryMailByMemberId(
            @PathVariable("memberId") Long memberId, @RequestParam(value = "page")int page, @RequestParam(value = "size")int size
    ){
        return new BaseResponse(HttpStatus.OK.value(), "친구 메일이 검색 되었습니다.",mailService.inquiryMailByFriendId(memberId, page, size));
    }

    @GetMapping("/friendMail/detail/{mailId}")
    public BaseResponse<MailDetailResponse> inquiryMailByMailId(@PathVariable("mailId") Long mailId){
        return new BaseResponse(HttpStatus.OK.value(), "메일 정보를 불러왔습니다.", mailService.inquiryMailDetailByMailId(mailId));
    }
}
