package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.common.BaseErrorResponse;
import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.domian.entity.EmailVerification;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.exception.BadRequestException;
import kwthon_1team.kwthon.exception.BaseException;
import kwthon_1team.kwthon.repository.EmailVerificationRepository;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final EmailVerificationRepository emailVerificationRepository;

    @Transactional
    public void join(AuthRequestDto authRequestDto) {
        validateAuthRequest(authRequestDto);
        checkDuplicateEmail(authRequestDto.getEmail());
        // verifyEmailCode(authRequestDto.getEmail(), Integer.valueOf(authRequestDto.getVerificationCode()));
        memberRepository.save(converToMember(authRequestDto));
    }

    @Transactional (readOnly = true)
    public void checkDuplicateEmail(String email) {
        String trimmedEmail = email.trim();
        if (!memberRepository.findByEmail(trimmedEmail).stream().toList().isEmpty()) {
            throw new BaseException(400, "이미 회원가입된 이메일");
        }
        validateUniversityEmail(trimmedEmail);
    }

    @Transactional (readOnly = true)
    public void validateUniversityEmail(String email) {
        System.out.println("이메일 도메인 체크하겠음");
        if (!email.endsWith("@kw.ac.kr")) {
            throw new BaseException(400, "광운대학교 이메일(@kw.ac.kr)만 입력해 주세요.");
        }
    }

    private void validateAuthRequest(AuthRequestDto authRequestDto) {
        if (authRequestDto.getStudentId() == null)
            throw new BadRequestException("아이디를 입력해 주세요.");
        if (authRequestDto.getDepartment() == null)
            throw new BadRequestException("학과를 입력해 주세요.");
        if (authRequestDto.getPassword() == null)
            throw new BadRequestException("비밀번호를 입력해 주세요.");
        if (authRequestDto.getName() == null)
            throw new BadRequestException("이름을 입력해 주세요.");
        if (authRequestDto.getEmail() == null)
            throw new BadRequestException("이메일을 입력해 주세요.");
        if (!authRequestDto.isAgreement())
            throw new BadRequestException("개인정보 수집에 동의해 주세요.");
    }

    private Member converToMember(AuthRequestDto authRequestDto) {
        return new Member(authRequestDto);
    }

    @Transactional
    public void requestEmailVerification(String email) {
        validateUniversityEmail(email);
        checkDuplicateEmail(email);

        // 인증 코드 생성 및 저장
        Integer verificationCode = generateVerificationCode();
        LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(10);  // 유효 기간 10분
        EmailVerification emailVerification = new EmailVerification(email, verificationCode, expirationDate);
        emailVerificationRepository.save(emailVerification);

        emailService.sendValidateEmailRequestMessage(email, verificationCode.toString());
    }

    private Integer generateVerificationCode() {
        return (int)(Math.random() * 1000000);
    }

    @Transactional
    public void verifyEmailCode(String email, Integer code) {
        EmailVerification emailVerification = emailVerificationRepository.findByEmail(email)
                .orElseThrow(()->new BadRequestException("이메일 인증이 필요합니다."));

        if (emailVerification.getVerificationCode() == null ||
            !emailVerification.getVerificationCode().equals(code)) {
            throw new BadRequestException("인증번호가 일치하지 않습니다.");
        }
        emailVerificationRepository.delete(emailVerification);
    }
}
