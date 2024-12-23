package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByStudentId(Long studentId);
    @Query("SELECT m FROM Member m WHERE " +
       "(m.name LIKE CONCAT(:keyword, '%') " +
       "OR m.department LIKE CONCAT(:keyword, '%') " +
       "OR CAST(m.studentId AS string) LIKE CONCAT(:keyword, '%'))")
    List<Member> findAllByKeyword(@Param("keyword") String keyword);
}
