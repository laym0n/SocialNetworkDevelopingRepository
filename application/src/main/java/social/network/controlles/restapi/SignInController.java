package social.network.controlles.restapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import social.network.dto.requests.SignUpRequest;
import social.network.usecases.usersusecases.SignInUseCase;

//@Controller
//@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = {"Authorization", "Content-Type"})
public class SignInController {
//    private SignInUseCase signInUseCase;
//    @PostMapping("/registration")
//    @ResponseBody
//    public void addUser(@RequestBody SignUpRequest request) {
//        signInUseCase.signUp(request);
//    }
}
