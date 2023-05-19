package social.network.usecases.getupdatesusecases.impl.handlerimpls;

import social.network.daservices.HandlerGetUpdateChatsDAService;
import social.network.dto.requests.UpdateRequest;
import social.network.dto.responses.GetUpdateResponse;
import social.network.usecases.getupdatesusecases.impl.HandlerGetUpdate;

public class HandlerGetUpdateChatsImpl implements HandlerGetUpdate {
    private HandlerGetUpdateChatsDAService daService;
    @Override
    public void getUpdateForUserById(UpdateRequest request, GetUpdateResponse response) {

    }
}
