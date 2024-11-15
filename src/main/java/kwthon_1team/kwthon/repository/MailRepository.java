package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
}
