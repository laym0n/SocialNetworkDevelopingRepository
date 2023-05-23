package social.network.usecases.getinfosusecases.impl.findusers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.FindUsersDAService;
import social.network.dto.requests.FindUsersByFirstAndSecondNamesRequest;
import social.network.dto.responses.FindUserResponse;
import social.network.entities.user.UserInfo;
import social.network.usecases.getinfosusecases.FindUsersUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class FindUsersUseCaseImpl implements FindUsersUseCase {
    private FindUsersDAService daService;
    @Override
    public FindUserResponse findUsersByFirstAndSecondNames(FindUsersByFirstAndSecondNamesRequest request) {
        List<UserInfo> allUsersWithContainingSearchString = daService
                .findUsersWithFirstNameAndSecondNameContainingSearchString(request.getSearchString());
        return new FindUserResponse(allUsersWithContainingSearchString);
    }
}
