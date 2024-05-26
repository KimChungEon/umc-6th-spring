package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.dto.request.StoreRequestDto;

public class MissionConverter {

    public static Mission toMission(StoreRequestDto.MissionRequestDto request, Store store){

        return Mission.builder()
                .deadline(request.getDeadline())
                .store(store)
                .missionSpec(request.getMission_spec())
                .reward(request.getReward())
                .build();
    }
}
