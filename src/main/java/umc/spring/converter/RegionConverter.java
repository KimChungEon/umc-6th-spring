package umc.spring.converter;

import umc.spring.domain.Region;

public class RegionConverter {

    public static Region toRegion(String regionName){
        return Region.builder()
                .name(regionName)
                .build();
    }
}
