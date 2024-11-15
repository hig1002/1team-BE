package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE " +
            "(m.name LIKE%:keyword% OR m.department LIKE%:keyword% OR CAST(m.studentId AS String) LIKE %:keyword%)")
    List<Member> findAllByKeyword(@Param("keyword") String keyword);
}
