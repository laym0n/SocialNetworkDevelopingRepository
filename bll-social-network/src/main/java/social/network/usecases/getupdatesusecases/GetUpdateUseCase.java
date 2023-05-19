package social.network.usecases.getupdatesusecases;

import social.network.dto.requests.UpdateRequest;
import social.network.dto.responses.GetUpdateResponse;

public interface GetUpdateUseCase {

    GetUpdateResponse getUpdateForUserById(UpdateRequest request);
}
