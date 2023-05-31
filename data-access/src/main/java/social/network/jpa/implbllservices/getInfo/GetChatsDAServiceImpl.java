package social.network.jpa.implbllservices.getInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.GetChatsDAService;
import social.network.dto.ChatDescriptionDTO;
import social.network.dto.modelsdto.messages.MessageDTO;
import social.network.jpa.dao.ChatDAO;
import social.network.jpa.dao.ChatMemberDAO;
import social.network.jpa.dao.MessageDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.ChatEntity;
import social.network.jpa.entities.ChatMemberEntity;
import social.network.jpa.entities.MessageEntity;
import social.network.jpa.entities.UserEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class GetChatsDAServiceImpl implements GetChatsDAService {
    private UserDAO userDAO;
    private ChatMemberDAO chatMemberDAO;
    private ChatDAO chatDAO;
    private MessageDAO messageDAO;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ChatDescriptionDTO> findAllDescriptionsForUser(int idUser) {
        List<Integer> chatIds = chatMemberDAO.findAllChatIdsWithUser(idUser);
        List<ChatEntity> chatEntities = chatDAO.findAllById(chatIds);
        return chatEntities.stream()
                .map(i-> {
                    ChatDescriptionDTO result = null;
                    if (i.getType().getName().equals("DIALOG_CHAT")) {
                        Set<Integer> idsMembers = new HashSet<>(
                                entityManager
                                        .createQuery("select cm.userId from ChatMemberEntity cm " +
                                        "where cm.chatId = :chatId").setParameter("chatId", i.getId())
                                .getResultList());
                        idsMembers.remove(idUser);
                        UserEntity user = userDAO.findById(idsMembers.stream().findFirst().get()).get();
                        result = new ChatDescriptionDTO(i.getId(),
                                user.getFirstName() + " " + user.getSecondName(),
                                Optional.ofNullable(user.getAvatar()));
                    } else {
                        result = new ChatDescriptionDTO(i.getId(), i.getName(), Optional.ofNullable(i.getAvatar()));
                    }
                    return result;
                })
                .toList();
    }

    @Override
    public List<MessageDTO> findAllMessageDTOForChat(int idChat) {
        List<MessageEntity> messageEntities = messageDAO.findAllForChat(idChat);
        List<MessageDTO> messageDTOS = messageEntities.stream().map(MessageEntity::getMessageDTO).toList();
        messageDTOS.forEach(messageDTO -> {
            ChatMemberEntity chatMemberEntity = chatMemberDAO.findById(messageDTO.getUserIdOwner());
            messageDTO.setUserIdOwner(chatMemberEntity.getUserId());
        });
        return messageDTOS;
    }

    @Override
    public ChatDescriptionDTO findDescriptionForChat(int idChat, int idUser) {
        ChatEntity chatEntity = chatDAO.findById(idChat);
        ChatDescriptionDTO result;
        if (chatEntity.getType().getName().equals("DIALOG_CHAT")) {
            Set<Integer> idsMembers = new HashSet<>(
                    entityManager
                            .createQuery("select cm.userId from ChatMemberEntity cm " +
                                    "where cm.chatId = :chatId").setParameter("chatId", idChat)
                            .getResultList());
            idsMembers.remove(idUser);
            UserEntity user = userDAO.findById(idsMembers.stream().findFirst().get()).get();
            result = new ChatDescriptionDTO(idChat,
                    user.getFirstName() + " " + user.getSecondName(),
                    Optional.ofNullable(user.getAvatar()));
        } else {
            result = new ChatDescriptionDTO(idChat, chatEntity.getName(), Optional.ofNullable(chatEntity.getAvatar()));
        }
        return result;
    }

    @Override
    public boolean isFrozenDialogChat(int idChat) {
        ChatEntity chatEntity = chatDAO.findById(idChat);

        return (chatEntity.getType().getName().equals("DIALOG_CHAT") ? chatEntity.isFrozenDueToBlacklist() : false);
    }
}
