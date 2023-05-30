package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.network.dto.requests.*;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.entities.user.PersonalInfo;
import social.network.entities.user.UserRole;
import social.network.security.RoleSecurity;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.CheckUserProfileUseCase;
import social.network.usecases.usersusecases.EditUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

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
        if (response.getUserProfile().getAvatar().isEmpty() || response.getUserProfile().getAvatar().get().length == 0) {
            try {
                response.getUserProfile().setAvatar(
                        Optional.of(
                                Files.readAllBytes(
                                        resourceLoader.getResource("classpath:User.png").getFile().toPath()
                                )
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String base64Image = Base64.getEncoder().encodeToString(response.getUserProfile().getAvatar().get());
        model.addAttribute("response", response);
        model.addAttribute("avatar64", base64Image);
        model.addAttribute("isUserOwner", true);
        model.addAttribute("isCurrentUserAdmin", false );
        return "profile";
    }
    @GetMapping("/{id}")
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user,
                                  Model model,
                                  @PathVariable("id") int idUserForCheckProfile) throws AccountNotFoundException {
        CheckUserProfileResponse response =
                checkUserProfileUseCase
                        .checkUserProfile(
                                new CheckUserProfileRequest(user.getUserId(), idUserForCheckProfile, 5)
                        );
        if (response.getUserProfile().getAvatar().isEmpty() || response.getUserProfile().getAvatar().get().length == 0) {
            try {
                response.getUserProfile().setAvatar(
                        Optional.of(
                                Files.readAllBytes(
                                        resourceLoader.getResource("classpath:User.png").getFile().toPath()
                                )
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String base64Image = Base64.getEncoder().encodeToString(response.getUserProfile().getAvatar().get());
        model.addAttribute("response", response);
        model.addAttribute("avatar64", base64Image);
        model.addAttribute("isUserOwner", false);
        model.addAttribute(
                "isCurrentUserAdmin",
                user.getAuthorities().contains(
                    new RoleSecurity(UserRole.ADMIN.name())
                )
        );
        return "profile";
    }

    @GetMapping("/update")
    public String updateProfileInfo(@AuthenticationPrincipal UserSecurity user, Model model) throws AccountNotFoundException {
        CheckUserProfileResponse response =
                checkUserProfileUseCase
                        .checkUserProfile(
                                new CheckUserProfileRequest(user.getUserId(), user.getUserId(), 5)
                        );
        UpdateProfileInfoRequest request = new UpdateProfileInfoRequest(
                response.getUserProfile().getPersonalInfo().getFirstName(),
                response.getUserProfile().getPersonalInfo().getSecondName(),
                response.getUserProfile().getPersonalInfo().getBirthday().orElse(LocalDate.now())
        );
        model.addAttribute("request", request);
        return "update-profile";
    }

    @GetMapping("/update/avatar")
    public String showProfileInfo(Model model) {
        model.addAttribute("request", new UpdateAvatarAppRequest());
        return "update-avatar";
    }

    @PostMapping("/update/avatar")
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user,
                                  @ModelAttribute UpdateAvatarAppRequest request) throws IOException {
        editUserProfileUseCase.updateAvatar(
                new UpdateAvatarRequest(
                        user.getUserId(),
                        Optional.ofNullable((request.getAvatar() == null ? null : request.getAvatar().getBytes()))
                )
        );
        return "redirect:/profile";
    }

    @PostMapping("/update")
    public String updateAvatar(@AuthenticationPrincipal UserSecurity user,
                               @ModelAttribute UpdateProfileInfoRequest request) {
        UpdatePersonalInfoRequest updatePersonalInfoRequest =
                new UpdatePersonalInfoRequest(
                        user.getUserId(),
                        new PersonalInfo(request.getFirstName(),
                                request.getSecondName(),
                                Optional.ofNullable(request.getBirthday())
                        )
                );
        editUserProfileUseCase.updatePersonalInfo(updatePersonalInfoRequest);
        return "redirect:/profile";
    }
}
