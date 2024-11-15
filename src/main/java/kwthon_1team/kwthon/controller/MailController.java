package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.domian.dto.response.MailBoxResponseDto;
import kwthon_1team.kwthon.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/kwTree/mailBoxList")
    public MailBoxResponseDto getMailBoxList() {
        return mailService.getMailBoxList(); // studentId를 전달하지 않음
    }
}
