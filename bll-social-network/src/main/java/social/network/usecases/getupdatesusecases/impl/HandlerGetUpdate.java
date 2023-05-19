package social.network.usecases.getupdatesusecases.impl;

import social.network.dto.requests.UpdateRequest;
import social.network.dto.responses.GetUpdateResponse;

public interface HandlerGetUpdate {
    void getUpdateForUserById(UpdateRequest request, GetUpdateResponse response);
}
