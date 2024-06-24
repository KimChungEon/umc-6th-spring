package umc.spring.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.RegionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.dto.request.StoreRequestDto;
import umc.spring.repository.*;
import umc.spring.validation.annotation.ExistStore;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    String url = "https://umc-6th.s3.ap-northeast-2.amazonaws.com/";

    /**
     * 워크북 9주차
     * */
    //미션 1. 특정 지역에 가게 추가하기 API
    public Long addStore(@Valid StoreRequestDto.NewStoreRequestDto storeRequestDto){

        Region region = RegionConverter.toRegion(storeRequestDto.getRegionName());
        regionRepository.save(region);

        Store newStore = StoreConverter.toStore(storeRequestDto, region);
        storeRepository.save(newStore);

        return newStore.getId();
    }

    //미션 2. 가게에 리뷰 추가하기 API
    public Long addReview(@ExistStore Long storeId, @Valid StoreRequestDto.ReviewRequestDto request, MultipartFile multipartFile){

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), multipartFile);

        Store findStore = storeRepository.findById(storeId).orElseThrow(null);
        findStore.setScore(request.getScore());

        Review review = ReviewConverter.toReview(request,findStore);
        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl,review));
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

    /**
     * 워크북 10주차
     * */
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).orElseThrow();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page-1, 10));

        return StorePage;
    }

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public Page<Mission> getMissionList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).orElseThrow();

        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page-1, 10));

        return missionPage;
    }

    public void deleteReviewImage(Long reviewImageId){
        ReviewImage reviewImage = reviewImageRepository.findById(reviewImageId).orElseThrow(null);

        if (reviewImage == null){
            throw new RuntimeException("존재하지 않는 이미지 파일입니다.");
        }
        String keyName = reviewImage.getImage_url();

        log.info("DeleteFile keyName: {}", keyName);
        keyName = keyName.substring(url.length());

        log.info("DeleteFile keyName: {}", keyName);

        s3Manager.deleteFile(keyName);

        reviewImageRepository.delete(reviewImage);
    }

}
