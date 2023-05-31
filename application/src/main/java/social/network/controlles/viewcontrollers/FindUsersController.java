package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.dto.UserAppDTO;
import social.network.dto.requests.FindUsersByFirstAndSecondNamesRequest;
import social.network.dto.requests.FindUsesAppRequest;
import social.network.entities.socialnetworkuser.HumanUser;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.FindUsersUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

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
