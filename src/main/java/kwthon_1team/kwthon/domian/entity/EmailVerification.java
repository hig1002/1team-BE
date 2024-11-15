package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    private String email;
    private Integer verificationCode;
    private LocalDateTime expirationTime;

    public EmailVerification(String email, Integer verificationCode, LocalDateTime expirationTime) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.expirationTime = expirationTime;
    }

    public EmailVerification() {

    }
}
