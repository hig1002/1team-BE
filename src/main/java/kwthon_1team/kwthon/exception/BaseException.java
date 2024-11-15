package kwthon_1team.kwthon.exception;

import kwthon_1team.kwthon.common.BaseErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private final int code;
    private final String message;

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
