package umc.spring.apiPayload.code;

import org.springframework.web.ErrorResponse;

public interface BaseErrorCode {
    public ErrorReasonDTO getReason();

    public ErrorReasonDTO getReasonHttpStatus();
}
