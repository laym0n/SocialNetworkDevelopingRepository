package social.network.controlles.viewcontrollers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import social.network.dto.ChatAppDTO;
import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.GetChatsRequest;
import social.network.dto.requests.ReadChatRequest;
import social.network.dto.responses.GetChatsResponse;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.GetChatsUseCase;

@Controller
@RequestMapping("/chats")
@AllArgsConstructor
public class ChatsController {
    private GetChatsUseCase getChatsUseCase;
    @GetMapping
    public String getChats(@AuthenticationPrincipal UserSecurity user, Model model) {
        GetChatsResponse response = getChatsUseCase
                .getChatsForUser(new GetChatsRequest(user.getUserId()));
        model.addAttribute("chats", response.getChats().stream().map(ChatAppDTO::new).toList());
        return "chats";
    }
    @GetMapping("/{id}")
    public String getChats(@AuthenticationPrincipal UserSecurity user, Model model, @PathVariable("id") int idChat) {
        ChatDTO chatDTO = getChatsUseCase.readChat(
                new ReadChatRequest(user.getUserId(), idChat)
        );
        model.addAttribute("messages", chatDTO.getMessages());
        model.addAttribute("description", new ChatAppDTO(chatDTO.getDescription()));
        model.addAttribute("isFrozen", chatDTO.isFrozen());
        return "read_chat";
    }
}
