package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.response.MemberMissionResponseDto;
import umc.spring.dto.response.StoreResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Mission mission){

        return MemberMission.builder()
                .mission(mission)
                //.member(member)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionPreViewListDTO challenginMissionPreViewListDTO(Page<MemberMission> memberMissionList){

        List<MemberMissionResponseDto.MemberMissionPreViewDTO> missionPreViewDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::memberMissionPreViewDTO).collect(Collectors.toList());

        return MemberMissionResponseDto.MemberMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .memberMissionList(missionPreViewDTOList)
                .build();
    }

    public static MemberMissionResponseDto.MemberMissionPreViewDTO memberMissionPreViewDTO(MemberMission memberMission){
        return MemberMissionResponseDto.MemberMissionPreViewDTO.builder()
                .mission_spec(memberMission.getMission().getMissionSpec())
                .deadline(memberMission.getMission().getDeadline())
                .reward(memberMission.getMission().getReward())
                .createdAt(memberMission.getCreatedAt())
                .status(memberMission.getStatus().toString())
                .build();
    }
}
