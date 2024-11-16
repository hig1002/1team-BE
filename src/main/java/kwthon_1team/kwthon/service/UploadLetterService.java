package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.request.UploadLetterRequestDto;
import kwthon_1team.kwthon.domian.dto.response.UploadLetterResponseDto;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Member;
import kwthon_1team.kwthon.domian.entity.Photo;
import kwthon_1team.kwthon.repository.MailRepository;
import kwthon_1team.kwthon.repository.MemberRepository;
import kwthon_1team.kwthon.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadLetterService {
    private final MailRepository mailRepository;
    private final S3Uploader s3Uploader;
    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;

    public UploadLetterResponseDto registerLetter(Long memberId, UploadLetterRequestDto request) throws IOException {
        Member sender = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Sender not found with id : " + memberId));
        Member receiver = memberRepository.findById(request.getReceiverId())
                .orElseThrow(()-> new IllegalArgumentException("Receiver not found with id : " + request.getReceiverId()));

        //photo 처리
        List<MultipartFile> photos = request.getPhotos();


        Mail mail = new Mail();
        mail.setMailTitle(request.getMailTitle());
        mail.setMailText(request.getMailText());
        mail.setIsPublic(request.getIsPublic());
        mail.setSender(sender);
        mail.setReceiver(receiver);
        mail.setMailDate(LocalDateTime.now());

        mail = mailRepository.save(mail);

        List<Photo> photoEntities = new ArrayList<>();
        for(MultipartFile photo : photos) {
            String photoUrl = s3Uploader.upload(photo, "mail-photos");

            Photo photoEntity = new Photo();
            photoEntity.setPhotoUrl(photoUrl);
            photoEntity.setMail(mail);
            photoEntities.add(photoEntity);
        }
        photoRepository.saveAll(photoEntities);

        mail.setPhotos(photoEntities);

        return UploadLetterResponseDto.builder()
                .mailId(mail.getMailId())
                .build();
    }
}
