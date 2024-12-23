package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.response.MailDetailResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;
import kwthon_1team.kwthon.service.MailService;
import kwthon_1team.kwthon.service.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kwTree")
public class KwTreeController {
    private final MailServiceImpl mailService;

    @GetMapping("/mailBoxList")
    public BaseResponse<MailPagingResponse<MailSummaryResponse>> inquiryKwTreeMail(@RequestParam(value = "page")int page,
                                                                            @RequestParam(value = "size")int size){
        Long memberId = 0L;
        return new BaseResponse(HttpStatus.OK.value(), "광운 메일 정보를 불러왔습니다.",mailService.inquiryMailByFriendId(memberId,page,size));
    }

    @GetMapping("/mailBox/{mailId}")
    public BaseResponse<MailDetailResponse> inquiryKwTreeMailByMailId(@PathVariable("mailId") Long mailId) {
        return new BaseResponse(HttpStatus.OK.value(), "광운 메일 정보를 불러왔습니다.", mailService.inquiryMailDetailByMailId(mailId));
    }
}