package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;
import umc.spring.validation.validator.PageCheckValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션을 만들 때 붙임.
@Constraint(validatedBy = PageCheckValidator.class)   //java에서 제공하는 사용자가 validation을 커스텀 어노테이션을 통해 할 수 있도록 제공.
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })  //어노테이션의 적용 범위를 지정
@Retention(RetentionPolicy.RUNTIME) //어노테이션의 생명 주기를 지정.
public @interface CheckPage {

    String message() default "page 번호가 음수입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
