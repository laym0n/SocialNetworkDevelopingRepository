package social.network.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.jpadao.JPAFriendDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FriendDAO {
    private JPAFriendDAO jpaFriendDAO;
    @PersistenceContext
    private EntityManager entityManager;

    public boolean existsWithUserIds(int firstUser, int secondUser) {
        return jpaFriendDAO.existsWithUserIds(firstUser, secondUser);
    }

    public void create(FriendEntity newFriend) {
        jpaFriendDAO.save(newFriend);
    }

    public int deleteFriendWithUsers(int idFirstUser, int idSecondUser) {
        int count = jpaFriendDAO.deleteFriendWithUsers(idFirstUser, idSecondUser);
        if (count != 0) {
            detachEntity(new FriendEntityId(idFirstUser, idSecondUser));
            detachEntity(new FriendEntityId(idSecondUser, idFirstUser));
        }
        return count;
    }
    private void detachEntity(FriendEntityId id) {
        FriendEntity entity = entityManager.find(FriendEntity.class, id);
        if (entity != null) {
            entityManager.detach(entity);
        }
    }

    public Optional<FriendEntity> findFriendEntityWithUsers(Integer firstIdUser, Integer secondIdUser) {
        return jpaFriendDAO.findFriendEntityWithUsers(firstIdUser, secondIdUser);
    }
}
