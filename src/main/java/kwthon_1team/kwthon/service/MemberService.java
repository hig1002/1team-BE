package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.converter.MemberConverter;
import kwthon_1team.kwthon.domian.dto.response.SearchResponse;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    public List<SearchResponse> search(String keyword) {
        List<Member> memberList = memberRepository.findAllByKeyword(keyword);
        return memberList.stream().map(memberConverter::toSearchResponse).toList();
    }
}
