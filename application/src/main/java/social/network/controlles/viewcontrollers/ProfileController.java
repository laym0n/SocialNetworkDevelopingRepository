package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.requests.UpdatePersonalInfoRequest;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.CheckUserProfileUseCase;
import social.network.usecases.usersusecases.EditUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private EditUserProfileUseCase editUserProfileUseCase;
    private CheckUserProfileUseCase checkUserProfileUseCase;
    private ResourceLoader resourceLoader;

    @GetMapping
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user, Model model) throws AccountNotFoundException {
        CheckUserProfileResponse response =
                checkUserProfileUseCase
                        .checkUserProfile(
                                new CheckUserProfileRequest(user.getUserId(), user.getUserId(), 5)
                        );
        if (response.getAvatar() == null || response.getAvatar().length == 0) {
            try {
                response.setAvatar(
                        Files.readAllBytes(
                                resourceLoader.getResource("classpath:User.png").getFile().toPath()
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String base64Image = Base64.getEncoder().encodeToString(response.getAvatar());
        model.addAttribute("response", response);
        model.addAttribute("avatar64", base64Image);
        return "profile";
    }
    @GetMapping("/update")
    public String updateProfileInfo(@AuthenticationPrincipal UserSecurity user, Model model) throws AccountNotFoundException {
        CheckUserProfileResponse response =
            checkUserProfileUseCase
                    .checkUserProfile(
                            new CheckUserProfileRequest(user.getUserId(), user.getUserId(), 5)
                    );
        model.addAttribute("response", response);
        return "update-profile";
    }
    @PostMapping("/update/avatar")
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user,
                                  @ModelAttribute CheckUserProfileResponse response,
                                  Model model) throws AccountNotFoundException {
//        UpdatePersonalInfoRequest request = UpdatePersonalInfoRequest.builder()
//                        .firstName(response.getFirstName())
//                        .secondName(response.getSecondName())
//                        .newBirthday(response.getBirthday())
//                        .idOwnerRequest(user.getUserId())
//                        .build();
//        editUserProfileUseCase.updatePersonalInfo(request);
        return "redirect:profile";
    }
    @PostMapping("/update")
    public String updateAvatar(@AuthenticationPrincipal UserSecurity user,
                                    @ModelAttribute UpdatePersonalInfoRequest request,
                                    Model model) throws AccountNotFoundException {
        request.setIdOwnerRequest(user.getUserId());
        editUserProfileUseCase.updatePersonalInfo(request);
        return "profile";
    }
}
