package social.network.usecases.getinfosusecases;

import social.network.dto.requests.FindUsersByFirstAndSecondNamesRequest;
import social.network.entities.socialnetworkuser.HumanUser;

import java.util.List;

public interface FindUsersUseCase {
    List<HumanUser> findUsersByFirstAndSecondNames(FindUsersByFirstAndSecondNamesRequest request);
}
