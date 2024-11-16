package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.domian.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByMail_MailId(Long mailId);
}
