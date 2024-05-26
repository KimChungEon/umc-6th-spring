package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;


    //미션 4. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
    public Long acceptMission(Long missionId){
        Mission mission = missionRepository.findById(missionId).orElseThrow(null);

        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission);
        memberMissionRepository.save(memberMission);

        return memberMission.getId();
    }

}