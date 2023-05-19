package social.network.usecases.usersusecases;

import social.network.dto.requests.SignInRequest;
import social.network.dto.requests.SignUpRequest;
import social.network.dto.responses.SignInResponse;

public interface SignInUseCase {
    SignInResponse signIn(SignInRequest signInRequest);

    void signUp(SignUpRequest signUpRequest);
}
