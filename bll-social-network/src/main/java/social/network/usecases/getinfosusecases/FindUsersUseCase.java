package social.network.usecases.getinfosusecases;

import social.network.dto.requests.FindUsersByFirstAndSecondNamesRequest;
import social.network.dto.responses.FindUserResponse;

public interface FindUsersUseCase {
    FindUserResponse findUsersByFirstAndSecondNames(FindUsersByFirstAndSecondNamesRequest request);
}
