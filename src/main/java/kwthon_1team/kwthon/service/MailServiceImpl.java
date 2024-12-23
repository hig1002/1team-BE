package kwthon_1team.kwthon.service;


import kwthon_1team.kwthon.converter.MailConverter;
import kwthon_1team.kwthon.domian.dto.response.MailDetailResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;
import kwthon_1team.kwthon.domian.entity.Mail;
import kwthon_1team.kwthon.domian.entity.Photo;
import kwthon_1team.kwthon.exception.BadRequestException;
import kwthon_1team.kwthon.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;
    private final MailConverter mailConverter;

    /*
     * 메일 검색
     */
    @Override
    public MailPagingResponse<MailSummaryResponse> inquiryMailByFriendId(Long memberId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Mail> mailPage = mailRepository.findAllByFriendId(memberId, pageable);

        //System.out.println(mailPage.getContent());
        Page<MailSummaryResponse> mailSummaryResponses = mailPage.map(mail->{
            Photo firstPhoto = mail.getPhotos() != null && !mail.getPhotos().isEmpty()
                    ? mail.getPhotos().get(0)
                    : null;
            return mailConverter.toMailSummaryResponse(mail,firstPhoto);
        });

        return mailConverter.toMailPagingResponse(mailSummaryResponses);
    }

    @Override
    public MailDetailResponse inquiryMailDetailByMailId(Long mailId){
        Mail mail = mailRepository.findByMailId(mailId)
                .orElseThrow(()-> new BadRequestException("존재하지 않는 메일입니다."));

        return mailConverter.toMailDetailResponse(mail);
    }
}
