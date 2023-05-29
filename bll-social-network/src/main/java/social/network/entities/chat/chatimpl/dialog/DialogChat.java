package social.network.entities.chat.chatimpl.dialog;

import social.network.entities.chat.Chat;
import social.network.entities.chat.ChatMember;

public class DialogChat extends Chat {
    private ChatMember firstChatMember;
    private ChatMember secondChatMember;
    private boolean isFrozenDueToBlackList = false;
}
