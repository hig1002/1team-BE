package kwthon_1team.kwthon.domian.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
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
    @JsonIgnore
    private Member sender;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "receiver")
    @JsonIgnore
    private Member receiver;

    @OneToMany (mappedBy = "mail", cascade = CascadeType.PERSIST, orphanRemoval = true)
    //@JsonManagedReference
    @JsonIgnore
    private List<Photo> photos = new ArrayList<>();
}
