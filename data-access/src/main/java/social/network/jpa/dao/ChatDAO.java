package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.ChatEntity;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.jpadao.JPAChatDAO;

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
}
