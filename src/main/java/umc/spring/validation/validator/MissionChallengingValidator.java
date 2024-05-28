package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.MissionChallenging;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<MissionChallenging, List<Long>> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(MissionChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> memberMissionRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}