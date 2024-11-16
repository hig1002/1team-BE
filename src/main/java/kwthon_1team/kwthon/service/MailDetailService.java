package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.response.MailBoxResponseDto;
import kwthon_1team.kwthon.domian.dto.response.MailDetailDto;
import kwthon_1team.kwthon.domian.dto.response.PhotoDto;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.repository.MailDetailRepository;
import kwthon_1team.kwthon.repository.MemberRepository;
import kwthon_1team.kwthon.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailDetailService {

    private final MailDetailRepository mailDetailRepository;
    private final PhotoRepository photoRepository;
    private final MemberRepository memberRepository;

    // 메일 상세 조회 서비스
    public MailBoxResponseDto getMailDetail(Long mailId) {
        Optional<Mail> mailOpt = mailDetailRepository.findById(mailId);

        if (mailOpt.isEmpty()) {
            return new MailBoxResponseDto(404, "메일 정보를 찾을 수 없습니다.", null);
        }

        Mail mail = mailOpt.get();

        // receiverId와 senderId에 해당하는 Member의 name을 조회
        String receiverName = memberRepository.findById(mail.getReceiver().getStudentId())
                .map(Member::getName)
                .orElse("이름을 찾을 수 없음");

        String senderName = memberRepository.findById(mail.getSender().getStudentId())
                .map(Member::getName)
                .orElse("이름을 찾을 수 없음");

        List<PhotoDto> photos = photoRepository.findByMail_MailId(mailId).stream()
                .map(photo -> new PhotoDto(photo.getPhotoId(), photo.getPhotoUrl())) // error 값은 기본적으로 null로 전달됩니다.
                .collect(Collectors.toList());

        // MailDetailDto에 조회한 receiverName과 senderName을 포함
        MailDetailDto data = new MailDetailDto(
                mail.getMailTitle(),           // mailTitle
                mail.getMailText(),            // mailText
                mail.getReceiver().getStudentId(), // receiverId
                receiverName,                  // receiverName
                mail.getSender().getStudentId(),   // senderId
                senderName,                    // senderName
                mail.getIsPublic(),            // isPublic
                photos                         // photos
        );

        return new MailBoxResponseDto(200, "광운 메일 정보를 불러왔습니다", data);
    }
}
