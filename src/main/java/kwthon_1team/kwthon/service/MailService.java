package kwthon_1team.kwthon.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsContructor
public class MailService {

    private final MailRepository mailRepository;

    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public MailBoxResponseDto getPublicMailBox(Long studentId, int receiver) {
        List<Mail> mails = mailRepository.findByReceiverAndIsPublic(receiver, true);
        if (mails.isEmpty()) {
            return new MailBoxResponseDto(404, "메일 정보를 찾을 수 없습니다.", null);
        }

        List<MailDto> mailDtos = mails.stream()
                .map(mail -> new MailDto(mail.getMailDate().toString(), mail.getMailName(), mail.getSenderId(), mail.getName()))
                .collect(Collectors.toList());

        MailBoxDataDto data = new MailBoxDataDto(mailDtos);
        return new MailBoxResponseDto(200, "광운 메일 정보를 불러왔습니다", data);
    }
}