package social.network.controlles.viewcontrollers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import social.network.dto.ChatAppDTO;
import social.network.dto.modelsdto.ChatDTO;
import social.network.dto.requests.GetChatsRequest;
import social.network.dto.requests.ReadChatRequest;
import social.network.dto.responses.GetChatsResponse;
import social.network.jpa.entities.MessageEntity;
import social.network.jpa.entities.ids.MessageEntityId;
import social.network.security.UserSecurity;
import social.network.usecases.getinfosusecases.GetChatsUseCase;

@Controller
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    @PersistenceContext
    private EntityManager entityManager;
    @PostMapping("/send")
    @Transactional
    public String getChats(@AuthenticationPrincipal UserSecurity user, Model model,
                           @RequestParam("idChat") int idChat,
                           @RequestParam("text") String text) {
        Integer idChatMember = (Integer) entityManager.createQuery("select cm.id from ChatMemberEntity cm " +
                        "where cm.userId = :id and cm.chatId = :chatId")
                .setParameter("id", user.getUserId())
                .setParameter("chatId", idChat)
                .getSingleResult();
        Integer orderId = (Integer) entityManager.createQuery("select max(m.id.orderId) from MessageEntity m " +
                "where m.id.chatId = :chatId").setParameter("chatId", idChat).getSingleResult();
        MessageEntity messageEntity = new MessageEntity(
                new MessageEntityId(idChat, (orderId == null? 0 : orderId) + 1),
                idChatMember,
                text,
                null
        );
        entityManager.persist(messageEntity);
        return "redirect:/chats/" + idChat;
    }
}
