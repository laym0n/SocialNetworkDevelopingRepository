package social.network.usecases.usersusecases.impl.signup;

import social.network.daservices.SignUpDAService;
import social.network.dto.requests.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.entities.user.User;
import social.network.entities.user.UserRole;
import social.network.usecases.usersusecases.SignUpUseCase;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {
    private static Logger log = Logger.getLogger(SignUpUseCaseImpl.class.getName());
    private SignUpDAService signUpDAService;

    @Override
    public void signUp(SignUpRequest request) {
        log.info("Get SignUpRequest " + request);
        User userForSave = request.getUser();
        userForSave.getRoles().put(UserRole.SIMPLE_USER.getIdGroup(), UserRole.SIMPLE_USER);
        signUpDAService.createUserWithPersonalInfo(userForSave, request.getUserProfile());
    }
}
