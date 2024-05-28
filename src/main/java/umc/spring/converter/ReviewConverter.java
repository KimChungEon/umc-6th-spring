package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.dto.request.StoreRequestDto;

public class ReviewConverter {

    public static Review toReview(StoreRequestDto.ReviewRequestDto request, Store store){
        return Review.builder()
                .body(request.getBody())
                //.member(member)
                .score(request.getScore())
                .reviewImageList(request.getReviewImages())
                .store(store)
                .build();
    }
}
