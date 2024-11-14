package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/auth/signUp")
    public BaseResponse signUp(@RequestBody AuthRequestDto authRequestDto) {
        memberService.join(authRequestDto);
        return new BaseResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
    }
}
