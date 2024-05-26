package umc.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.domain.Member;
import umc.spring.dto.request.StoreRequestDto;
import umc.spring.service.StoreService;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/new")
    public ApiResponse<Object> addStore(@Valid @RequestBody StoreRequestDto.NewStoreRequestDto request){



        return ApiResponse.onSuccess(storeService.addStore(request));
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<Object> addReview(@ExistStore @PathVariable Long storeId,
                                         @Valid @RequestBody StoreRequestDto.ReviewRequestDto request){
        return ApiResponse.onSuccess(storeService.addReview(storeId, request));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<Object> addMission(@ExistStore @PathVariable Long storeId,
                                          @Valid @RequestBody StoreRequestDto.MissionRequestDto request){
        return ApiResponse.onSuccess(storeService.addMission(storeId, request));
    }
}
