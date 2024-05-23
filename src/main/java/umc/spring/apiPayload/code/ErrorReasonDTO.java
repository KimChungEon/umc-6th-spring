package umc.spring.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorReasonDTO {
    private String message;
    private String code;
    private Boolean isSuccess;
    private HttpStatus httpStatus;
}
