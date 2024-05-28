package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.dto.request.StoreRequestDto;

public class StoreConverter {

    public static Store toStore(StoreRequestDto.NewStoreRequestDto storeRequestDto, Region region){

        return Store.builder()
                .name(storeRequestDto.getStoreName())
                .address(storeRequestDto.getAddress())
                .region(region)
                .build();
    }
}
