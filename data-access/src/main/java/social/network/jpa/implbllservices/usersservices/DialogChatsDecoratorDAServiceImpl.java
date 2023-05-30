package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.DialogChatsDecoratorDAService;
import social.network.jpa.dao.ChatDAO;
import social.network.jpa.entities.ChatEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DialogChatsDecoratorDAServiceImpl implements DialogChatsDecoratorDAService {
    private ChatDAO chatDAO;
    @Override
    public void setIsFrozenDueToBlackListIfExists(int idSecondUser, int idFirstUser, boolean value) {
        Optional<Integer> idDialogChat = chatDAO.findIdDialogChatBetweenUsers(idFirstUser, idSecondUser);
        if (idDialogChat.isEmpty()) {
            return;
        }
        ChatEntity dialog = chatDAO.findById(idDialogChat.get());
        dialog.setFrozenDueToBlacklist(value);
        chatDAO.updateEntity(dialog);
    }
}
