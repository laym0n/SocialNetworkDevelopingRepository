package social.network.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.BlackListUserEntity;
import social.network.entities.ids.BlackListUserEntityId;

@Repository
public interface UsersBlackListDAO extends CrudRepository<BlackListUserEntity, BlackListUserEntityId> {

    @Query("SELECT CASE WHEN COUNT(blu) > 0 THEN true ELSE false END " +
            "FROM BlackListUserEntity blu " +
            "WHERE (blu.id.userBlockedId = ?1 AND blu.id.userOwnerId = ?2) " +
            "OR (blu.id.userBlockedId = ?2 AND blu.id.userOwnerId = ?1)")
    boolean existsAnyWithUsers(int firstUser, int secondUser);
}
