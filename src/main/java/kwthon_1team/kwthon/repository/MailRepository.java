package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    // 공개된 메일만 조회
    List<Mail> findByIsPublic(boolean isPublic);
}