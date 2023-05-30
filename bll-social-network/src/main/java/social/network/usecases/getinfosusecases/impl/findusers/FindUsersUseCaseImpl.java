package social.network.usecases.getinfosusecases.impl.findusers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.FindUsersDAService;
import social.network.dto.requests.FindUsersByFirstAndSecondNamesRequest;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.usecases.getinfosusecases.FindUsersUseCase;

import java.util.List;

@Service
@AllArgsConstructor
public class FindUsersUseCaseImpl implements FindUsersUseCase {
    private FindUsersDAService daService;
    @Override
    public List<HumanUser> findUsersByFirstAndSecondNames(FindUsersByFirstAndSecondNamesRequest request) {
        List<HumanUser> allUsersWithContainingSearchString = daService
                .findUsersWithFirstNameAndSecondNameContainingSearchString(request.getSearchString());
        return allUsersWithContainingSearchString;
    }
}
