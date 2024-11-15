import kwthon_1team.kwthon.domian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    // memberId로 트리 데이터를 찾는 메서드 정의
    Tree findByMemberId(Long memberId);
}
