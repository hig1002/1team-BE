package kwthon_1team.kwthon.domian.entity;

import jakarta.persistence.*;
import kwthon_1team.kwthon.domian.dto.request.AuthRequestDto;
import kwthon_1team.kwthon.exception.BaseException;
import lombok.*;
import org.springframework.http.HttpStatus;

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

    @Column (name = "email", unique = true)
    private String email;

    @Column
    private String password;

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

    public void validatePassword(String password) {
        if (!password.equals(this.password)) {
            throw new BaseException(HttpStatus.UNAUTHORIZED.value(), "비밀번호 일치하지 않음");
        }
    }
}
