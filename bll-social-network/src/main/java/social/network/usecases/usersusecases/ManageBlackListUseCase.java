package social.network.usecases.usersusecases;

import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;

public interface ManageBlackListUseCase {
    void addUserToBlackList(AddUserToBlackListRequest request);
    void removeFromBlackList(RemoveUserFromBlackListRequest request);
}
