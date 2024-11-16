package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.response.MailDetailResponse;
import kwthon_1team.kwthon.domian.dto.response.MailPagingResponse;
import kwthon_1team.kwthon.domian.dto.response.MailSummaryResponse;

public interface MailService {

    MailPagingResponse<MailSummaryResponse> inquiryMailByFriendId(Long memberId, int page, int size);
    MailDetailResponse inquiryMailDetailByMailId(Long mailId);
}