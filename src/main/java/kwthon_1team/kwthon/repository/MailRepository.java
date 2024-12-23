package kwthon_1team.kwthon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import kwthon_1team.kwthon.domian.entity.Mail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import java.util.Optional;


public interface MailRepository extends JpaRepository<Mail,Long> {

    //@Query("SELECT m FROM Mail m JOIN m.sender s WHERE  m.isPublic = true AND s.studentId = :memberId")
    @Query("SELECT distinct m FROM Mail m where m.isPublic = true AND m.receiver.studentId = :memberId order by m.mailDate desc")
    Page<Mail> findAllByFriendId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("select distinct m from Mail m JOIN fetch m.photos where m.mailId = :mailId and m.isPublic = true")
    Optional<Mail> findByMailId(@Param("mailId") Long mailId);

    @Query("select m from Mail m where m.sender.studentId = :memberId ")
    Page<Mail> findAllSenderByMyId(@Param("memberId")Long memberId, Pageable pageable);

    @Query("select m from Mail m where m.receiver.studentId = :memberId ")
    Page<Mail> findAllReceiverByMyId(@Param("memberId")Long memberId, Pageable pageable);

    @Query("select m from Mail m where m.mailId = :mailId and (m.sender.studentId = :memberId or m.receiver.studentId = :memberId)")
    Mail findMyMailByMailId(@Param("memberId")Long memberId, @Param("mailId") Long mailId);
}
