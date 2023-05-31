package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import social.network.dto.UserAppDTO;
import social.network.dto.requests.CheckFriendsRequest;
import social.network.dto.requests.DeleteFriendRequest;
import social.network.dto.responses.CheckFriendsResponse;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.CheckFriendsUseCase;
import social.network.usecases.usersusecases.ManageFriendsUseCase;

import java.util.List;

@Controller
@RequestMapping("/friend")
@AllArgsConstructor
public class FriendController {
    private ManageFriendsUseCase manageFriendsUseCase;
    private CheckFriendsUseCase checkFriendsUseCase;

    @PostMapping(value = "/delete")
    public String acceptRequest(@AuthenticationPrincipal UserSecurity user,
                                @RequestParam("idUserFriend") int idUserFriend) {
        manageFriendsUseCase.deleteFriend(
                new DeleteFriendRequest(
                        user.getUserId(),
                        idUserFriend
                )
        );
        return "redirect:/profile/" + idUserFriend;
    }

    @GetMapping
    public String acceptRequest(@AuthenticationPrincipal UserSecurity user, Model model) {
        CheckFriendsResponse response = checkFriendsUseCase.checkFriends(
                new CheckFriendsRequest(user.getUserId())
        );
        List<UserAppDTO> userAppDTOS = response.getFriends().stream().map(UserAppDTO::new).toList();
        model.addAttribute("users", userAppDTOS);
        return "friends";
    }
}
