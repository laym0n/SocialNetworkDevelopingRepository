package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import social.network.dto.requests.RegistrationRequest;
import social.network.dto.requests.SignUpRequest;
import social.network.usecases.usersusecases.SignInUseCase;

import javax.validation.Valid;
import java.io.IOException;

@Component
@Controller
@AllArgsConstructor
public class SignInController {
    private SignInUseCase signInUseCase;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
//        model.addAttribute("userExists", false);
        model.addAttribute("request", new RegistrationRequest());
        return "registration";
    }
    @PostMapping(value = "/registration")
    public String signUp(@ModelAttribute RegistrationRequest request, Model model) throws IOException {
        SignUpRequest signUpRequest = request.getSignUpRequest();
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        try {
            signInUseCase.signUp(signUpRequest);
        } catch (DuplicateKeyException exception) {
            model.addAttribute("userExists", true);
            model.addAttribute("request", request);
            return "registration";
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return "redirect:/login";
    }
}
