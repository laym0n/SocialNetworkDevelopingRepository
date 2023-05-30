package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.dto.UserAppDTO;
import social.network.dto.requests.*;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.entities.user.PersonalInfo;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.CheckUserProfileUseCase;
import social.network.usecases.getinfosusecases.FindUsersUseCase;
import social.network.usecases.usersusecases.EditUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/findusers")
public class FindUsersController {
    private FindUsersUseCase findUsersUseCase;

    @GetMapping
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user, Model model) throws AccountNotFoundException {
        model.addAttribute("request", new FindUsesAppRequest());
        return "find-users";
    }

    @PostMapping()
    public String showProfileInfo(@AuthenticationPrincipal UserSecurity user,
                                  @ModelAttribute FindUsesAppRequest request,
                                  Model model) throws IOException {
        List<HumanUser> humanUsers = findUsersUseCase.findUsersByFirstAndSecondNames(
                new FindUsersByFirstAndSecondNamesRequest(user.getUserId(), request.getSearchString())
        );
        model.addAttribute("request", request);
        model.addAttribute("foundUsers", humanUsers.stream().map(UserAppDTO::new).toList());
        return "find-users";
    }
}
