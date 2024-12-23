package kwthon_1team.kwthon.domian.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Getter
@Builder
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long photoId;

    @Column
    private String photoUrl;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "mailId")
    @JsonIgnore
    private Mail mail;
}
