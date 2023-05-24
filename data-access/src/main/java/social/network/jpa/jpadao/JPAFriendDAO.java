package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.ids.FriendEntityId;

import java.util.Optional;

@Repository
public interface JPAFriendDAO extends CrudRepository<FriendEntity, FriendEntityId> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM FriendEntity f " +
            "WHERE (f.id.firstUserId = ?1 AND f.id.secondUserId = ?2) " +
            "OR (f.id.firstUserId = ?2 AND f.id.secondUserId = ?1)")
    boolean existsWithUserIds(Integer firstUserId, Integer secondUserId);
    @Query("SELECT f " +
            "FROM FriendEntity f " +
            "WHERE (f.id.firstUserId = ?1 AND f.id.secondUserId = ?2) " +
            "OR (f.id.firstUserId = ?2 AND f.id.secondUserId = ?1)")
    Optional<FriendEntity> findFriendEntityWithUsers(int firstUser, int secondUser);

    @Modifying
    @Query("DELETE " +
            "FROM FriendEntity f " +
            "WHERE (f.id.firstUserId = ?1 AND f.id.secondUserId = ?2) " +
            "OR (f.id.firstUserId = ?2 AND f.id.secondUserId = ?1)")
    int deleteFriendWithUsers(int idUserFriend, int idUserSenderRequest);
}
