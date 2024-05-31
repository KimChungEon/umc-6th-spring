package umc.spring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;
import umc.spring.domain.ReviewImage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreRequestDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewStoreRequestDto{
        private String storeName;
        private String  address;
        private String regionName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewRequestDto{

        private String title;
        private String body;
        private Float score;
        private List<ReviewImage> reviewImages = new ArrayList<>();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionRequestDto{

        private Integer reward;
        private Date deadline;
        private String mission_spec;
    }



}
