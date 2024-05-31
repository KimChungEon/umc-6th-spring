package umc.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.dto.response.MemberMissionResponseDto;
import umc.spring.dto.response.StoreResponseDto;
import umc.spring.service.MemberMissionService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.MissionChallenging;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member-missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @PostMapping("/accept/{missionId}")
    @Operation(summary = "미션 도전하기 API",description = "멤버가 미션 도전")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<Long> acceptMission(@MissionChallenging @PathVariable Long missionId){

        return ApiResponse.onSuccess(memberMissionService.acceptMission(missionId));
    }

    @PatchMapping("/success/{missionId}")
    @Operation(summary = "도전중인 미션 완료 API",description = "도전 중인 미션 완료")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<Long> successMission(@MissionChallenging @PathVariable Long missionId, Long memberId){

        return ApiResponse.onSuccess(memberMissionService.successMission(missionId,memberId));
    }

    @GetMapping("/{memberId}/member_missions")
    @Operation(summary = "특정 멤버가 진행중인 미션 목록 조회 API",description = "특정 멤버가 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponseDto.MemberMissionPreViewListDTO> getMissionList(@PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){

        return ApiResponse.onSuccess(MemberMissionConverter.challenginMissionPreViewListDTO(memberMissionService.getChallengingMissionList(memberId,page)));
    }
}
