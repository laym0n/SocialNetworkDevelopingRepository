package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import social.network.dto.requests.CreateDialogChatRequest;
import social.network.security.UserSecurity;
import social.network.usecases.chatsusecases.ManageDialogsChatsUseCase;

import java.io.IOException;

@Controller
@RequestMapping("/dialogchat")
@AllArgsConstructor
public class DialogChatController {
    private ManageDialogsChatsUseCase manageDialogsChatsUseCase;

    @PostMapping("/create")
    public String create(@AuthenticationPrincipal UserSecurity user,
                         @RequestParam("idUser") int idUser) throws IOException {
        manageDialogsChatsUseCase.createDialogChat(
                new CreateDialogChatRequest(user.getUserId(), idUser, null)
        );
        return "redirect:/profile/" + idUser;
    }
}
