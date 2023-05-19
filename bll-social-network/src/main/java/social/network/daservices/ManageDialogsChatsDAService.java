package social.network.daservices;

import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.chatimpl.dialog.DialogChat;
import social.network.entities.chat.messages.Message;

public interface ManageDialogsChatsDAService {
    boolean checkIfUsersInBlackListsEachOther(int idUserToDialog, int idUserSenderRequest);

    DialogChat saveDialogChatWithMessageAndSaveEvent(Message message, int idFirstUser, int idSecondUser, ChatCreatedEvent event);
}
