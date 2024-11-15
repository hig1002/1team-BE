import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsContructor
public class TreeService {

    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public TreeResponseDto getTreeInfo(Long memberId) {
        // 접근 권한 검증 로직
        boolean hasAccess = checkAccessPermission(memberId);
        if (!hasAccess) {
            return new TreeResponseDto(403, "이 트리 정보에 대한 접근 권한이 없습니다.", null);
        }

        // 트리 데이터를 데이터베이스에서 불러오기
        Tree tree = treeRepository.findByMemberId(memberId);
        if (tree == null) {
            return new TreeResponseDto(404, "트리 정보를 찾을 수 없습니다.", null);
        }

        TreeDataDto treeData = new TreeDataDto(tree.getTreeLevel());
        return new TreeResponseDto(200, "내 트리 정보를 불러왔습니다", treeData);
    }

    private boolean checkAccessPermission(Long memberId) {
        // 접근 권한 검증 로직
        return memberId != null && memberId > 0;
    }
}