package social.network.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageDialogsChatsDAService;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.chatimpl.dialog.DialogChat;
import social.network.entities.chat.messages.Message;

@Service
@AllArgsConstructor
public class ManageDialogsChatsDAServiceImpl implements ManageDialogsChatsDAService {

    @Override
    public boolean checkIfUsersInBlackListsEachOther(int idUserToDialog, int idUserSenderRequest) {
        return false;
    }

    @Override
    public DialogChat saveDialogChatWithMessageAndSaveEvent(Message message,
                                                            int idFirstUser,
                                                            int idSecondUser,
                                                            ChatCreatedEvent event) {
        return null;
    }
}
