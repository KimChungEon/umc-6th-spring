package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    //

    /**
     * 1번의 page 범위에 따라 커스텀 어노테이션은 page 1을 0으로 만들어 return 해야 한다
     * page 번호 음수 안됨.
     * 에러 발생 시 반드시 RestControllerAdvice와 연계를 해야 함
     * */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = false;

        if (value>0){
            isValid=true;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_IS_NOT_VALID.toString()).addConstraintViolation();
        }


        return isValid;
    }
}
