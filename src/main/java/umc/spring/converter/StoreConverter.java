package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.dto.request.StoreRequestDto;
import umc.spring.dto.response.MemberMissionResponseDto;
import umc.spring.dto.response.StoreResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.NewStoreRequestDto storeRequestDto, Region region){

        return Store.builder()
                .name(storeRequestDto.getStoreName())
                .address(storeRequestDto.getAddress())
                .region(region)
                .build();
    }
    public static Review toReview(StoreRequestDto.ReviewRequestDto request){
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDto.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDto.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDto.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDto.ReviewPreViewDTO.builder()
                //.ownerNickname(review.getMember().getName())
                .body(review.getBody())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }
    public static StoreResponseDto.ReviewPreViewListDTO reviewPreViewListDTO(List<Review> reviewList){
        return null;
    }


    public static StoreResponseDto.MissionPreViewDTO missionPreViewDTO(Mission mission){
        return StoreResponseDto.MissionPreViewDTO.builder()
                .reward(mission.getReward())
                .mission_spec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt())
                .build();
    }


}
