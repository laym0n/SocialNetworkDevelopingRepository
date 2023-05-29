package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.network.dto.requests.RegistrationRequest;
import social.network.dto.requests.SignUpRequest;
import social.network.usecases.usersusecases.SignUpUseCase;

import java.io.IOException;

@Component
@Controller
@AllArgsConstructor
public class SignUpController {
    private SignUpUseCase signUpUseCase;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("request", new RegistrationRequest());
        return "registration";
    }
    @PostMapping(value = "/registration")
    public String signUp(@ModelAttribute RegistrationRequest request, Model model) throws IOException {
        SignUpRequest signUpRequest = request.getSignUpRequest();
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        try {
            signUpUseCase.signUp(signUpRequest);
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
