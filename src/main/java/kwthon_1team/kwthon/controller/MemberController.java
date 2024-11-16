package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.dto.request.AuthDuplicationRequestDto;
import kwthon_1team.kwthon.domian.dto.request.AuthLoginRequestDto;
import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.domian.dto.request.AuthVerificationEmailRequestDto;
import kwthon_1team.kwthon.domian.dto.response.SearchResponse;
import kwthon_1team.kwthon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/search")
    public BaseResponse<List<SearchResponse>> search(@RequestParam String keyword) {
        return new BaseResponse(HttpStatus.OK.value(), "검색 결과를 불러왔습니다.", memberService.search(keyword));
    }

    @PostMapping("/auth/signUp")
    public BaseResponse signUp(@RequestBody AuthRequestDto authRequestDto) {
        memberService.join(authRequestDto);
        return new BaseResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
    }

    @PostMapping("/auth/emailDuplication")
    public BaseResponse duplicateEmail(@RequestBody AuthDuplicationRequestDto authDuplicationRequestDto) {
        memberService.checkDuplicateEmail(authDuplicationRequestDto.getEmail());
        return new BaseResponse(HttpStatus.OK.value(), "사용 가능한 이메일");
    }

    @PostMapping("/auth/emailVerification")
    public BaseResponse verificationEmail(@RequestBody AuthVerificationEmailRequestDto authVerificationEmailRequestDto) {
        memberService.verifyEmailCode(authVerificationEmailRequestDto.getEmail(), authVerificationEmailRequestDto.getCode());
        return new BaseResponse(HttpStatus.OK.value(), "사용자 이메일 인증 성공");
    }

    @PostMapping("/auth/login")
    public BaseResponse login(@RequestBody AuthLoginRequestDto authLoginRequestDto) {
        return new BaseResponse(HttpStatus.OK.value(), "로그인 성공",
                memberService.login(authLoginRequestDto.getStudentId(), authLoginRequestDto.getPassword()));
    }
}
