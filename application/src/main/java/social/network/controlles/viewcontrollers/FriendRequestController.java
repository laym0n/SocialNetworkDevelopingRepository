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
import social.network.dto.requests.AcceptFriendRequestRequest;
import social.network.dto.requests.CancelFriendRequestRequest;
import social.network.dto.requests.CheckFriendRequestsRequest;
import social.network.dto.requests.SendNewFriendRequestRequest;
import social.network.dto.responses.CheckFriendRequestsResponse;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.CheckFriendRequestsUseCase;
import social.network.usecases.usersusecases.ManageFriendRequestsUseCase;

@Controller
@RequestMapping("/friendrequest")
@AllArgsConstructor
public class FriendRequestController {
    private ManageFriendRequestsUseCase manageFriendRequestsUseCase;
    private CheckFriendRequestsUseCase checkFriendRequestsUseCase;

    @PostMapping(value = "/accept")
    public String acceptRequest(@AuthenticationPrincipal UserSecurity user,
                                @RequestParam("idUserSenderFriendRequest") int idUserSenderFriendRequest) {
        manageFriendRequestsUseCase.acceptRequest(
                new AcceptFriendRequestRequest(user.getUserId(), idUserSenderFriendRequest)
        );
        return "redirect:/profile/" + idUserSenderFriendRequest;
    }

    @PostMapping(value = "/cancel")
    public String cancelRequest(@AuthenticationPrincipal UserSecurity user,
                                @RequestParam("idUserSenderFriendRequest") int idUserSenderFriendRequest) {
        manageFriendRequestsUseCase.cancelRequest(
                new CancelFriendRequestRequest(user.getUserId(), idUserSenderFriendRequest)
        );
        return "redirect:/profile/" + idUserSenderFriendRequest;
    }

    @PostMapping(value = "/send")
    public String sendRequest(@AuthenticationPrincipal UserSecurity user,
                              @RequestParam("idUserToWhom") int idUserToWhom) {
        manageFriendRequestsUseCase.sendNewRequest(
                new SendNewFriendRequestRequest(user.getUserId(), idUserToWhom)
        );
        return "redirect:/profile/" + idUserToWhom;
    }

    @GetMapping
    public String findRequests(@AuthenticationPrincipal UserSecurity user, Model model) {
        CheckFriendRequestsResponse response = checkFriendRequestsUseCase
                .checkFriendRequests(new CheckFriendRequestsRequest(user.getUserId()));
        model.addAttribute("users", response.getUsers().stream().map(UserAppDTO::new).toList());
        return "friendrequests";
    }
}
