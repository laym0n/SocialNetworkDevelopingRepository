package social.network.implbllservices.usersservices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.FriendDAO;
import social.network.dao.FriendRequestDAO;
import social.network.daservices.FriendRelationshipsHandlerDAService;
import social.network.entities.FriendEntity;
import social.network.entities.FriendRequestEntity;
import social.network.entities.ids.FriendEntityId;
import social.network.entities.ids.FriendRequestEntityId;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendRelationshipsHandlerDAServiceImpl implements FriendRelationshipsHandlerDAService {
    private FriendDAO friendDAO;
    private FriendRequestDAO friendRequestDAO;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    @Override
    public void deleteFriendRelationshipAndFriendRequestIfExist(int firstUserId, int secondUserId) {
        int count = friendDAO.deleteFriendWithUsers(firstUserId, secondUserId);
        if (count != 0) {
            detachFriendEntities(firstUserId, secondUserId);
        }
        count = friendRequestDAO.deleteAllByContainingUsers(firstUserId, secondUserId);
        if (count != 0) {
            detachFriendRequestEntities(firstUserId, secondUserId);
        }

    }
    private void detachFriendRequestEntities(int firstUserId, int secondUserId) {
        FriendRequestEntity entity = entityManager.find(
                FriendRequestEntity.class,
                new FriendRequestEntityId(
                        firstUserId,
                        secondUserId
                )
        );
        if(entity != null) {
            entityManager.detach(entity);
        }
        entity = entityManager.find(
                FriendRequestEntity.class,
                new FriendRequestEntityId(
                        secondUserId,
                        firstUserId
                )
        );
        if(entity != null) {
            entityManager.detach(entity);
        }
    }
    private void detachFriendEntities(int firstUserId, int secondUserId) {
        FriendEntity entity = entityManager.find(
                FriendEntity.class,
                new FriendEntityId(
                        firstUserId,
                        secondUserId
                )
        );
        if(entity != null) {
            entityManager.detach(entity);
        }
        entity = entityManager.find(
                FriendEntity.class,
                new FriendEntityId(
                        secondUserId,
                        firstUserId
                )
        );
        if(entity != null) {
            entityManager.detach(entity);
        }
    }
}
