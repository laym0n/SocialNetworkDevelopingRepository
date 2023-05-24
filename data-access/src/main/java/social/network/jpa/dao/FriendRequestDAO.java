package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.FriendRequestEntity;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.jpa.jpadao.JPAFriendRequestDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FriendRequestDAO {
    private JPAFriendRequestDAO jpaFriendRequestDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public boolean isExistWithUsers(int idFirstUser, int idSecondUser) {
        return jpaFriendRequestDAO.isExistWithUsers(idFirstUser, idSecondUser);
    }

    public void create(FriendRequestEntity newFriendRequestEntity) {
        jpaFriendRequestDAO.save(newFriendRequestEntity);
    }

    public boolean existsById(FriendRequestEntityId friendRequestEntityId) {
        return jpaFriendRequestDAO.existsById(friendRequestEntityId);
    }

    public void deleteById(FriendRequestEntityId id) {
        jpaFriendRequestDAO.deleteById(id);
        detachMaybeLoadedEntity(id);
    }

    public void deleteAllById(FriendRequestEntityId id) {
        jpaFriendRequestDAO.deleteAllById(id);
        detachMaybeLoadedEntity(id);
    }

    public void deleteAllByContainingUsers(int firstUserId, int secondUserId) {
        int count = jpaFriendRequestDAO.deleteAllByContainingUsers(firstUserId, secondUserId);
        if (count != 0) {
            detachMaybeLoadedEntity(new FriendRequestEntityId(firstUserId, secondUserId));
            detachMaybeLoadedEntity(new FriendRequestEntityId(secondUserId, firstUserId));
        }
    }

    private void detachMaybeLoadedEntity(FriendRequestEntityId id) {
        FriendRequestEntity entity = entityManager.find(FriendRequestEntity.class, id);
        if (entity != null) {
            entityManager.detach(entity);
        }
    }

    public Optional<FriendRequestEntity> findById(FriendRequestEntityId id) {
        return jpaFriendRequestDAO.findById(id);
    }
}
