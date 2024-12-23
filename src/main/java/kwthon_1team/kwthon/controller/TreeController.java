package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseResponse;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.exception.BadRequestException;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TreeController {
    private final MemberRepository memberRepository;

    @GetMapping("/tree/{memberId}")
    public BaseResponse<Integer> getTreeLevel
            (@PathVariable("memberId") Long memberId) {
        Member member =  memberRepository.findByStudentId(memberId)
                .orElseThrow(()-> new BadRequestException("존재하지 않는 id 입니다."));

        Integer treeLevel = member.getTreeLevel();
        return new BaseResponse(HttpStatus.OK.value(), "트리 정보를 불러왔습니다.", treeLevel);
    }
}
