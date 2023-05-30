package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.dto.ChatDescriptionDTO;
import social.network.jpa.entities.ChatEntity;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.jpadao.JPAChatDAO;

import java.util.*;

@Repository
@AllArgsConstructor
public class ChatDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAChatDAO jpaChatDAO;

    public void create(ChatEntity newChatEntity) {
        jpaChatDAO.save(newChatEntity);
    }

    public String getTypeNameByIdChat(int idPrivateChat) {
        return (String) entityManager.createQuery("select c.type.name from ChatEntity c " +
                "where c.id = :idPrivateChat").setParameter("idPrivateChat", idPrivateChat).getSingleResult();
    }

    public void updateIsActiveForChat(int idChat, boolean isActive) {
        jpaChatDAO.updateIsActiveById(isActive, idChat);
        detachEntity(idChat);
    }
    public void updateAvatar(int idChat, byte[] newAvatar) {
        jpaChatDAO.updateAvatarById(newAvatar, idChat);
        detachEntity(idChat);
    }
    public void updateDescriptionAndName(int idChat, String newName, String newDescription) {
        jpaChatDAO.updateNameAndDescriptionById(newName, newDescription, idChat);
        detachEntity(idChat);
    }
    private void detachEntity(int id) {
        ChatEntity entity = entityManager.find(ChatEntity.class, id);
        if (entity != null) {
            entityManager.detach(entity);
        }
    }

    public List<ChatEntity> findAllById(Collection<Integer> chatIds) {
        return chatIds.stream().map(i->jpaChatDAO.findById(i).get()).toList();
    }

    public ChatEntity findById(int idChat) {
        return jpaChatDAO.findById(idChat).get();
    }

    public Optional<Integer> findIdDialogChatBetweenUsers(int idFirstUser, int idSecondUser) {
        List<Integer> idsDialogChat = entityManager.createQuery("select c.id from ChatEntity c " +
                "where c.type.name = 'DIALOG_CHAT'").getResultList();
        Optional<Integer> result = Optional.empty();
        for (int idChat : idsDialogChat) {
            Set<Integer> idsMembers = new HashSet<>(entityManager.createQuery("select cm.userId from ChatMemberEntity cm " +
                    "where cm.chatId = :chatId").setParameter("chatId", idChat)
                    .getResultList());
            if (idsMembers.contains(idFirstUser) && idsMembers.contains(idSecondUser)) {
                result = Optional.of(idChat);
                break;
            }
        }
        return result;
    }

    public void updateEntity(ChatEntity dialog) {
        jpaChatDAO.save(dialog);
    }
}
