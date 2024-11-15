package kwthon_1team.kwthon.controller;

import kwthon_1team.kwthon.common.BaseErrorResponse;
import kwthon_1team.kwthon.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kwTree")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mailBoxList/{studentId}")
    public ResponseEntity<MailBoxResponseDto> getMailBoxList(@PathVariable Long studentId, @RequestParam int receiver) {
        try {
            MailBoxResponseDto responseDto = mailService.getPublicMailBox(studentId, receiver);
            return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatus()));
        } catch (Exception e) {
            MailBoxResponseDto errorResponse = new MailBoxResponseDto(500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}