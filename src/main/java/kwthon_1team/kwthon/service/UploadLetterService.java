package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.converter.MailConverter;
import kwthon_1team.kwthon.domian.dto.request.UploadLetterRequestDto;
import kwthon_1team.kwthon.domian.dto.response.UploadLetterResponse;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.repository.MailRepository;
import kwthon_1team.kwthon.repository.MemberRepository;
import kwthon_1team.kwthon.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadLetterService {
    private final MailRepository mailRepository;
    private final S3Uploader s3Uploader;
    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;
    private final MailConverter mailConverter;
    private final PhotoService photoService;

    public UploadLetterResponse registerLetter(Long memberId, UploadLetterRequestDto request, List<MultipartFile> mailPhotos) throws IOException {
        Member sender = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Sender not found with id : " + memberId));
        Member receiver = memberRepository.findById(request.getReceiverId())
                .orElseThrow(()-> new IllegalArgumentException("Receiver not found with id : " + request.getReceiverId()));

        LocalDateTime mailDate = LocalDateTime.now();

        Mail mail = mailConverter.toMail(request, mailPhotos ,sender, receiver,mailDate);

        mail = mailRepository.save(mail);

        if (mailPhotos != null && !mailPhotos.isEmpty()) {
            photoService.createAndSavePhoto(mail, mailPhotos);
        }

        return UploadLetterResponse.builder()
                .mailId(mail.getMailId())
                .build();
    }
}
