package social.network.jpa.jpadao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.jpa.entities.BlackListUserEntity;
import social.network.jpa.entities.ids.BlackListUserEntityId;

@Repository
public interface JPAUsersBlackListDAO extends CrudRepository<BlackListUserEntity, BlackListUserEntityId> {

    @Query("SELECT CASE WHEN COUNT(blu) > 0 THEN true ELSE false END " +
            "FROM BlackListUserEntity blu " +
            "WHERE (blu.id.userBlockedId = ?1 AND blu.id.userOwnerId = ?2) " +
            "OR (blu.id.userBlockedId = ?2 AND blu.id.userOwnerId = ?1)")
    boolean existsAnyWithUsers(int firstUser, int secondUser);
}
