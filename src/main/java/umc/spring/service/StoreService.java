package umc.spring.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.RegionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.dto.request.StoreRequestDto;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    //미션 1. 특정 지역에 가게 추가하기 API
    public Long addStore(@Valid StoreRequestDto.NewStoreRequestDto storeRequestDto){

        Region region = RegionConverter.toRegion(storeRequestDto.getRegionName());
        regionRepository.save(region);

        Store newStore = StoreConverter.toStore(storeRequestDto, region);
        storeRepository.save(newStore);

        return newStore.getId();
    }

    //미션 2. 가게에 리뷰 추가하기 API
    public Long addReview(@ExistStore Long storeId, @Valid StoreRequestDto.ReviewRequestDto request){

        Store findStore = storeRepository.findById(storeId).orElseThrow(null);
        findStore.setScore(request.getScore());

        Review review = ReviewConverter.toReview(request,findStore);
        reviewRepository.save(review);

        return review.getId();
    }

    //미션 3. 가게에 미션 추가하기 API
    public Long addMission(@ExistStore Long storeId, @Valid StoreRequestDto.MissionRequestDto request){
        Store findStore = storeRepository.findById(storeId).orElseThrow(null);

        Mission mission = MissionConverter.toMission(request, findStore);
        missionRepository.save(mission);

        return mission.getId();
    }
}
