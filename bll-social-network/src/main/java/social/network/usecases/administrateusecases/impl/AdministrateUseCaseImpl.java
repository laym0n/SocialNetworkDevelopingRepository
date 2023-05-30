package social.network.usecases.administrateusecases.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.AdministrateDAService;
import social.network.dto.requests.BanUserRequest;
import social.network.dto.requests.UnbanUserRequest;
import social.network.usecases.administrateusecases.AdministrateUseCase;

@Service
@AllArgsConstructor
public class AdministrateUseCaseImpl implements AdministrateUseCase {
    private AdministrateDAService daService;
    @Override
    public void banUser(BanUserRequest request) {
        daService.setIsBlockedForUser(request.getIdUserForBan(), true);
    }

    @Override
    public void unbanUser(UnbanUserRequest request) {
        daService.setIsBlockedForUser(request.getIdUserForUnban(), false);
    }

    @Override
    public void deletePost(BanUserRequest request) {

    }

    @Override
    public void deleteComment(UnbanUserRequest request) {

    }
}
