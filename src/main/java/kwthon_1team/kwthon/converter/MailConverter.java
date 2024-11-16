package kwthon_1team.kwthon.converter;

import kwthon_1team.kwthon.domian.dto.response.MailDetailResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Photo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

@Component
@RequiredArgsConstructor
public class MailConverter {


    public MailSummaryResponse toMailSummaryResponse(Mail mail, Photo Photo){
        return MailSummaryResponse.builder()
                .mailId(mail.getMailId())
                .mailDate(mail.getMailDate())
                .mailTitle(mail.getMailTitle())
                .senderId(mail.getSender().getStudentId())
                .senderName(mail.getSender().getName())
                .isPublic(mail.getIsPublic())
                .photo(Photo)
                .build();
    }

    public MailDetailResponse toMailDetailResponse(Mail mail){
        return MailDetailResponse.builder()
                .mailId(mail.getMailId())
                .mailText(mail.getMailText())
                .mainTitle(mail.getMailTitle())
                .senderId(mail.getSender().getStudentId())
                .senderName(mail.getSender().getName())
                .isPublic(mail.getIsPublic())
                .mailDate(mail.getMailDate())
                .build();
    }

    public <T> MailPagingResponse<T> toMailPagingResponse(Page<T> mails){
        return MailPagingResponse.<T>builder()
                .emails(mails.getContent())
                .page(mails.getNumber())
                .totalPages(mails.getTotalPages())
                .isFirst(mails.isFirst())
                .isLast(mails.isLast())
                .totalElements(mails.getNumberOfElements())
                .build();
    }

}
