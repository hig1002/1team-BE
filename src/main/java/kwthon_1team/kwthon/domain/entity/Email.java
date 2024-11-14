package kwthon_1team.kwthon.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Email {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    private boolean isPublic;

    private LocalDateTime mailDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Email> sentEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Email> receivedEmail;
}
