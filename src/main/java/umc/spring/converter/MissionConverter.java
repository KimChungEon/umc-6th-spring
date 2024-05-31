package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.dto.request.StoreRequestDto;
import umc.spring.dto.response.StoreResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(StoreRequestDto.MissionRequestDto request, Store store){

        return Mission.builder()
                .deadline(request.getDeadline())
                .store(store)
                .missionSpec(request.getMission_spec())
                .reward(request.getReward())
                .build();
    }

    public static StoreResponseDto.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){

        List<StoreResponseDto.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(StoreConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDto.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
