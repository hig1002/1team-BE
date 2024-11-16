package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Getter
public class Photo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long photoId;

    @Column
    private String photoUrl;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "mailId")
    private Mail mail;
}
