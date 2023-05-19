package social.network.usecases.usersusecases.impl.signin;

import social.network.daservices.SignInDAService;
import social.network.dto.requests.SignInRequest;
import social.network.dto.requests.SignUpRequest;
import social.network.dto.responses.SignInResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.usecases.usersusecases.SignInUseCase;

@Service
@AllArgsConstructor
public class SignInUseCaseImpl implements SignInUseCase {
    private SignInDAService signInDAService;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        String password = signInDAService.getUserPasswordByUserName(signInRequest.getUserName());
        return new SignInResponse(password.equals(signInRequest.getPassword()));
    }

    @Override
    public void signUp(SignUpRequest request) {
        signInDAService.createSimpleUserWithUserInfo(request.getUser(), request.getUserInfo());
    }
}
