package social.network.usecases.getinfosusecases;

import social.network.dto.requests.CheckAvatarRequest;
import social.network.dto.responses.CheckAvatarResponse;

public interface CheckAvatarUseCase {
    CheckAvatarResponse loadAvatarForUser(CheckAvatarRequest request);
}
