package kwthon_1team.kwthon.repository;

import kwthon_1team.kwthon.domian.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByEmail(String email);


    @Query("SELECT ev FROM EmailVerification ev WHERE ev.email = :email ORDER BY ev.expirationTime DESC")
    Optional<EmailVerification> findLatestByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM email_verification WHERE email = :email ORDER BY expiration_time DESC LIMIT 1", nativeQuery = true)
    Optional<EmailVerification> findLatestByEmailNative(@Param("email") String email);
}
