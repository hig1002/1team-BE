package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.response.*;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Photo;
import kwthon_1team.kwthon.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyMailService {
    private final MailRepository mailRepository;

    public MailPagingResponse<MyMailDetailResponseDto> myMailByMemberId(Long memberId, int page, int size, String filter){
        //페이징 정보를 위함
        Pageable pageable = PageRequest.of(page,size, Sort.by("mailDate").descending());

        Page<Mail> mailPage;
        if("send".equalsIgnoreCase(filter)){
            mailPage = mailRepository.findAllSenderByMyId(memberId, pageable);
        } else if ("receive".equalsIgnoreCase(filter)) {
            mailPage = mailRepository.findAllReceiverByMyId(memberId, pageable);
        } else {
            throw new IllegalArgumentException("Invaild filter value : "+filter);
        }

        List<MyMailDetailResponseDto> mailDetails = mailPage.getContent().stream()
                .map(this::convertToMyMailDetailResponseDto)
                .collect(Collectors.toList());

        return MailPagingResponse.<MyMailDetailResponseDto>builder()
                .emails(mailDetails)
                .page(mailPage.getNumber())
                .totalPages(mailPage.getTotalPages())
                .totalElements((int)mailPage.getTotalElements())
                .isFirst(mailPage.isFirst())
                .isLast(mailPage.isLast())
                .build();
    }

    private MyMailDetailResponseDto convertToMyMailDetailResponseDto(Mail mail){
        //List photos
        List<PhotoDto> photos = mail.getPhotos().stream()
                .map(photo -> new PhotoDto(photo.getPhotoId(), photo.getPhotoUrl()))
                .collect(Collectors.toList());


        // photos 리스트에서 첫 번째 사진만 가져오기
        PhotoDto latestPhoto = photos.isEmpty() ? null : photos.get(0);

        return MyMailDetailResponseDto.builder()
                .mailId(mail.getMailId())
                .mailDate(mail.getMailDate())
                .mailTitle(mail.getMailTitle())
                .senderId(mail.getSender().getStudentId())
                .senderName(mail.getSender().getName())
                .isPublic(mail.getIsPublic())
                .photos(latestPhoto != null ? List.of(latestPhoto):List.of())
                .build();
    }

    public MyMailOneDetailResponse myMailDetailBymailId(Long memberId, Long mailId){
        Mail mail = mailRepository.findMyMailByMailId(memberId, mailId);

        return MyMailOneDetailResponse.builder()
                .mailId(mail.getMailId())
                .mailTitle(mail.getMailTitle())
                .mailText(mail.getMailText())
                .senderId(mail.getSender().getStudentId())
                .senderName(mail.getSender().getName())
                .receiverId(mail.getReceiver().getStudentId())
                .receiverName(mail.getReceiver().getName())
                .photos(mail.getPhotos().stream()
                        .map(this::converToPhototo)
                        .collect(Collectors.toList()))
                .build();
    }

    private PhotoDto converToPhototo(Photo photo){
        return PhotoDto.builder()
                .photoId(photo.getPhotoId())
                .photoUrl(photo.getPhotoUrl())
                .build();
    }
}
