package kwthon_1team.kwthon.service;


import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.exception.BadRequestException;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
  import kwthon_1team.kwthon.converter.MemberConverter;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    private String emailCode;

    @Transactional
    public void join(AuthRequestDto authRequestDto) {
        validateAuthRequest(authRequestDto);
        validateDuplicateEmail(authRequestDto.getEmail());
        memberRepository.save(converToMember(authRequestDto));
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

    private void validateDuplicateEmail(String email) {

    }

    private Member converToMember(AuthRequestDto authRequestDto) {
        return new Member(authRequestDto);

    public List<SearchResponse> search(String keyword) {
        List<Member> memberList = memberRepository.findAllByKeyword(keyword);
        return memberList.stream().map(memberConverter::toSearchResponse).toList();
    }
}
