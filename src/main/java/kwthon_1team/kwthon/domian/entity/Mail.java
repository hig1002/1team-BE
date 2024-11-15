package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;

    @Column (nullable = false)
    private String mailTitle;

    @Column (nullable = false)
    private String mailText;

    @Column (nullable = false)
    private Boolean isPublic;

    @Column (nullable = false)
    private LocalDateTime mailDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "sender")
    private Member sender;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "receiver")
    private Member receiver;

    @OneToMany (mappedBy = "mail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();
}
