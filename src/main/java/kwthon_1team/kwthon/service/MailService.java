package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.response.MailBoxResponseDto;
import kwthon_1team.kwthon.domian.dto.response.MailDto;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.repository.MailRepository;
import kwthon_1team.kwthon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final MemberRepository memberRepository; // MemberRepository 추가

    // studentId 파라미터 없이 전체 메일을 반환
    public MailBoxResponseDto getMailBoxList() {
        // public 메일 조회
        List<Mail> mails = mailRepository.findByIsPublic(true);

        if (mails.isEmpty()) {
            return new MailBoxResponseDto(404, "메일 정보를 찾을 수 없습니다.", null);
        }

        // MailDto로 변환
        List<MailDto> mailDtos = mails.stream()
                .map(mail -> {
                    // 송신자 이름 가져오기
                    String senderName = memberRepository.findByStudentId(mail.getSender().getStudentId()).getName();

                    // 사진 중 하나 랜덤으로 선택
                    String randomPhotoUrl = getRandomPhotoUrl(mail);
                    Long randomPhotoId = getRandomPhotoId(mail);

                    return new MailDto(
                            mail.getMailDate().toString(),
                            mail.getMailTitle(),
                            mail.getSender().getStudentId(),
                            senderName,
                            randomPhotoId, // 랜덤 사진 ID
                            randomPhotoUrl // 랜덤 사진 URL
                    );
                })
                .collect(Collectors.toList());

        MailBoxResponseDto.MailBoxDataDto data = new MailBoxResponseDto.MailBoxDataDto(mailDtos);
        return new MailBoxResponseDto(200, "광운 메일 정보를 불러왔습니다", data);
    }

    private String getRandomPhotoUrl(Mail mail) {
        return mail.getPhotos().isEmpty() ? null : mail.getPhotos().get((int) (Math.random() * mail.getPhotos().size())).getPhotoUrl();
    }

    private Long getRandomPhotoId(Mail mail) {
        return mail.getPhotos().isEmpty() ? null : mail.getPhotos().get((int) (Math.random() * mail.getPhotos().size())).getPhotoId();
    }
}