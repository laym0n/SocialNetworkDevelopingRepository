package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import social.network.dto.requests.BanUserRequest;
import social.network.dto.requests.UnbanUserRequest;
import social.network.security.UserSecurity;
import social.network.usecases.administrateusecases.AdministrateUseCase;

@Controller
@RequestMapping("/administrate")
@AllArgsConstructor
public class AdministrateController {
    private AdministrateUseCase administrateUseCase;

    @PostMapping("/ban")
    public String ban(@AuthenticationPrincipal UserSecurity user,
                      @RequestParam("idUserForBan") int idUserForBan) {
        administrateUseCase.banUser(
                new BanUserRequest(user.getUserId(), idUserForBan)
        );
        return "redirect:/profile/" + idUserForBan;
    }

    @PostMapping("/unban")
    public String unban(@AuthenticationPrincipal UserSecurity user,
                        @RequestParam("idUserForUnban") int idUserForUnban) {
        administrateUseCase.unbanUser(
                new UnbanUserRequest(user.getUserId(), idUserForUnban)
        );
        return "redirect:/profile/" + idUserForUnban;
    }
}
