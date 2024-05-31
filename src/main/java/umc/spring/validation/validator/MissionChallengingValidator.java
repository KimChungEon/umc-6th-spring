package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.MissionChallenging;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<MissionChallenging, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public void initialize(MissionChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Mission mission = missionRepository.findById(value).orElseThrow(null);
        boolean isValid = true;

        if (memberMissionRepository.existsByMission(mission)){
            isValid=false;
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}