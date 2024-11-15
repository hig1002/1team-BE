package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.response.TreeDataDto;
import kwthon_1team.kwthon.domian.dto.response.TreeResponseDto;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class TreeService {

    private final MemberRepository memberRepository;

    public TreeService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public TreeResponseDto getTreeInfo(Long studentId) {
        // 접근 권한 검증 로직
        boolean hasAccess = checkAccessPermission(String.valueOf(studentId));
        if (!hasAccess) {
            return new TreeResponseDto(403, "이 트리 정보에 대한 접근 권한이 없습니다.", null);
        }

        // 트리 데이터를 데이터베이스에서 불러오기
        Member member = memberRepository.findByStudentId(studentId);  // memberId로 조회
        if (member == null) {
            return new TreeResponseDto(404, "트리 정보를 찾을 수 없습니다.", null);
        }

        // 트리 레벨을 TreeDataDto로 반환
        TreeDataDto treeData = new TreeDataDto(member.getTreeLevel());
        return new TreeResponseDto(200, "내 트리 정보를 불러왔습니다", treeData);
    }

    private boolean checkAccessPermission(String memberId) {  // String으로 변경
        // 접근 권한 검증 로직
        return memberId != null && !memberId.isEmpty();
    }
}