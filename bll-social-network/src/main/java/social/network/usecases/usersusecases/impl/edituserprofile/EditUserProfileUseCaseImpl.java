package social.network.usecases.usersusecases.impl.edituserprofile;

import org.springframework.stereotype.Service;
import social.network.daservices.EditUserProfileDAService;
import social.network.dto.requests.UpdateAvatarRequest;
import social.network.dto.requests.UpdatePersonalInfoRequest;
import lombok.AllArgsConstructor;
import social.network.usecases.usersusecases.EditUserProfileUseCase;
import social.network.usecases.usersusecases.impl.manageblacklist.ManageBlackListUseCaseImpl;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class EditUserProfileUseCaseImpl implements EditUserProfileUseCase {
    private static Logger log = Logger.getLogger(EditUserProfileUseCaseImpl.class.getName());
    private EditUserProfileDAService daService;

    @Override
    public void updatePersonalInfo(UpdatePersonalInfoRequest request) {
        log.info("Get UpdatePersonalInfoRequest " + request);
        daService.updatePersonalInfo(request.getIdOwnerRequest(), request.getUpdatedPersonalInfo());
    }

    @Override
    public void updateAvatar(UpdateAvatarRequest request) {
        log.info("Get UpdateAvatarRequest " + request);
        daService.updateAvatarUser(request.getIdOwnerRequest(), request.getNewAvatar());
    }
}
