package social.network.jpa.implbllservices.chatsservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckerOwnerPrivateChatDAService;
import social.network.exceptions.EntityNotFoundException;
import social.network.jpa.dao.*;

@Service
@AllArgsConstructor
public class CheckerOwnerPrivateChatDAServiceImpl implements CheckerOwnerPrivateChatDAService {
    private static String PRIVATE_CHAT_TYPE_COLUMN_VALUE = "PRIVATE_CHAT";
    private ChatTypeDAO chatTypeDAO;
    private ChatDAO chatDAO;
    private ChatMemberDAO chatMemberDAO;
    @Override
    public Integer getOwnerPrivateChat(int idPrivateChat) {
        String chatType = chatDAO.getTypeNameByIdChat(idPrivateChat);
        if (!chatType.equals(PRIVATE_CHAT_TYPE_COLUMN_VALUE)) {
            throw new EntityNotFoundException("Private chat with id " + idPrivateChat
                    + " is not private chat");
        }
        Integer id = chatMemberDAO.findIdUserChatMemberOfChat(idPrivateChat);
        return id;
    }
}
