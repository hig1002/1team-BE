package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.*;
import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
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
    private Integer authentication;  // 이메일 인증번호

    @Column (nullable = false)
    private Integer treeLevel = 0;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mail> senders = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mail> receivers = new ArrayList<>();

    public Member(AuthRequestDto authRequestDto) {
        this.studentId = authRequestDto.getStudentId();
        this.department = authRequestDto.getDepartment();
        this.name = authRequestDto.getName();
        this.email = authRequestDto.getEmail();
        this.password = authRequestDto.getPassword();
    }

}
