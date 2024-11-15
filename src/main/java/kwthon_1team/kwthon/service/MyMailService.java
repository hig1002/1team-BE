package kwthon_1team.kwthon.service;

import kwthon_1team.kwthon.domian.dto.request.MailRequestDto;
import kwthon_1team.kwthon.domian.dto.response.MailListDataDto;
import kwthon_1team.kwthon.domian.dto.response.MailResponseDto;
import kwthon_1team.kwthon.domian.dto.response.PhotoDto;
import kwthon_1team.kwthon.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyMailService {
    private final MailRepository mailRepository;

    /*private List<PhotoDto> findPhoto(Long memberId, String filter, Long mailId){

    }

    private List<MailListDataDto> findMails(Long memberId, String filter){

    }

    public MailResponseDto.myMailListResultDto getMailList(MailRequestDto.myMailListDto request){

    }*/
}
