package umc.spring.service.tempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag) {

    }
}

