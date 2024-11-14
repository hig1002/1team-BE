package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Getter
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private Long studentId;

    @Column
    private String department;

    @Column (nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean isPublic;

    @Column
    private Integer authentication;  // 이메일 인증번호

    @Column (nullable = false)
    private Integer treeLevel;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Mail> senders = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Mail> receivers = new ArrayList<>();

}
