package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import social.network.dto.requests.CreatePrivateChatAppRequest;
import social.network.dto.requests.CreatePrivateChatRequest;
import social.network.security.UserSecurity;
import social.network.usecases.chatsusecases.ManagePrivateChatsUseCase;

import java.io.IOException;

@Controller
@RequestMapping("/privatechat")
@AllArgsConstructor
public class PrivateChatController {
    private ManagePrivateChatsUseCase managePrivateChatsUseCase;

    @GetMapping("/create")
    public String create(Model model) throws IOException {
        model.addAttribute("request", new CreatePrivateChatAppRequest());
        return "create-private-chat";
    }

    @PostMapping("/create")
    public String create(@AuthenticationPrincipal UserSecurity user,
                         @ModelAttribute CreatePrivateChatAppRequest request) throws IOException {
        managePrivateChatsUseCase.createPrivateChat(
                new CreatePrivateChatRequest(
                        user.getUserId(),
                        request.getPrivateChatProfile()
                )
        );
        return "redirect:/chats";
    }
}
