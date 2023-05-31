package social.network.usecases.administrateusecases.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.AdministrateDAService;
import social.network.dto.requests.BanUserRequest;
import social.network.dto.requests.UnbanUserRequest;
import social.network.usecases.administrateusecases.AdministrateUseCase;
import social.network.usecases.usersusecases.impl.edituserprofile.EditUserProfileUseCaseImpl;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AdministrateUseCaseImpl implements AdministrateUseCase {
    private static Logger log = Logger.getLogger(AdministrateUseCaseImpl.class.getName());
    private AdministrateDAService daService;
    @Override
    public void banUser(BanUserRequest request) {
        log.info("Get BanUserRequest " + request);
        daService.setIsBlockedForUser(request.getIdUserForBan(), true);
    }

    @Override
    public void unbanUser(UnbanUserRequest request) {
        log.info("Get UnbanUserRequest " + request);
        daService.setIsBlockedForUser(request.getIdUserForUnban(), false);
    }

    @Override
    public void deletePost(BanUserRequest request) {

    }

    @Override
    public void deleteComment(UnbanUserRequest request) {

    }
}
