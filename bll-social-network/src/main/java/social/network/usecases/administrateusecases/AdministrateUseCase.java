package social.network.usecases.administrateusecases;

import social.network.dto.requests.BanUserRequest;
import social.network.dto.requests.UnbanUserRequest;

public interface AdministrateUseCase {
    void banUser(BanUserRequest request);

    void unbanUser(UnbanUserRequest request);

    void deletePost(BanUserRequest request);

    void deleteComment(UnbanUserRequest request);
}
