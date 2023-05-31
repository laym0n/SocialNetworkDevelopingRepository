package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import social.network.dto.requests.AddUserToBlackListRequest;
import social.network.dto.requests.RemoveUserFromBlackListRequest;
import social.network.security.UserSecurity;
import social.network.usecases.usersusecases.ManageBlackListUseCase;

@Controller
@RequestMapping("/blacklist")
@AllArgsConstructor
public class BlackListController {
    private ManageBlackListUseCase manageBlackListUseCase;

    @PostMapping(value = "/add")
    public String addToBlackList(@AuthenticationPrincipal UserSecurity user,
                                 @RequestParam("idUserForBlock") int idUserForBlock) {
        manageBlackListUseCase.addUserToBlackList(
                new AddUserToBlackListRequest(
                        user.getUserId(),
                        idUserForBlock
                )
        );
        return "redirect:/profile/" + idUserForBlock;
    }

    @PostMapping(value = "/remove")
    public String removeFromBlackList(@AuthenticationPrincipal UserSecurity user,
                                      @RequestParam("idUserForRemove") int idUserForRemove) {
        manageBlackListUseCase.removeFromBlackList(
                new RemoveUserFromBlackListRequest(
                        user.getUserId(),
                        idUserForRemove
                )
        );
        return "redirect:/profile/" + idUserForRemove;
    }
}
