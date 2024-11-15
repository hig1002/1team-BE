import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsContructor
public class MailDetailService {

    private final MailDetailRepository mailDetailRepository;
    private final PhotoRepository photoRepository;
    private final MemberRepository memberRepository;

    public MailDetailService(MailDetailRepository mailDetailRepository, PhotoRepository photoRepository, MemberRepository memberRepository) {
        this.mailDetailRepository = mailDetailRepository;
        this.photoRepository = photoRepository;
        this.memberRepository = memberRepository;
    }

    public MailBoxResponseDto getMailDetail(Long mailId) {
        Optional<Mail> mailOpt = mailRepository.findById(mailId);

        if (mailOpt.isEmpty()) {
            return new MailBoxResponseDto(404, "메일 정보를 찾을 수 없습니다.", null);
        }

        Mail mail = mailOpt.get();

        // receiverId와 senderId에 해당하는 Member의 name을 조회
        String receiverName = memberRepository.findByMemberId(mail.getReceiverId())
                .map(Member::getName)
                .orElse("이름을 찾을 수 없음");

        String senderName = memberRepository.findByMemberId(mail.getSenderId())
                .map(Member::getName)
                .orElse("이름을 찾을 수 없음");

        List<PhotoDto> photos = photoRepository.findByMailId(mailId).stream()
                .map(photo -> new PhotoDto(photo.getPhotoId(), photo.getPhotoUrl()))
                .collect(Collectors.toList());

        // MailDetailDto에 조회한 receiverName과 senderName을 포함
        MailDetailDto data = new MailDetailDto(
                mail.getMailName(),
                mail.getMailText(),
                mail.getReceiverId(),
                receiverName,
                mail.getSenderId(),
                senderName,
                mail.isPublic(),
                photos
        );

        return new MailBoxResponseDto(200, "광운 메일 정보를 불러왔습니다", data);
    }
}