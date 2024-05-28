package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Member;
import umc.spring.service.MemberMissionService;
import umc.spring.validation.annotation.MissionChallenging;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PatchMapping("/accept/{missionId}")
    public ApiResponse<Long> acceptMission(@MissionChallenging @PathVariable Long missionId){

        return ApiResponse.onSuccess(memberMissionService.acceptMission(missionId));
    }
}
