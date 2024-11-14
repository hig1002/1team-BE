package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private String emailCode;

    @Transactional
    public void join(AuthRequestDto authRequestDto) {
        validateAuthRequest(authRequestDto);
        validateDuplicateEmail(authRequestDto.getEmail());
        memberRepository.save(converToMember(authRequestDto));
    }


}
