package social.network.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import social.network.entities.FriendRequestEntity;
import social.network.entities.ids.FriendRequestEntityId;

@Repository
public interface FriendRequestDAO extends CrudRepository<FriendRequestEntity, FriendRequestEntityId> {
    @Query("SELECT CASE WHEN COUNT(fr) > 0 THEN true ELSE false END " +
            "FROM FriendRequestEntity fr " +
            "WHERE (fr.id.userSenderRequestId = ?1 AND fr.id.userToWhomId = ?2) " +
            "OR (fr.id.userSenderRequestId = ?2 AND fr.id.userToWhomId = ?1)")
    boolean isExistWithUsers(int firstIdUser, int secondIdUser);

    int deleteAllById(FriendRequestEntityId id);

    @Modifying
    @Query("DELETE FROM FriendRequestEntity fr " +
            "WHERE (fr.id.userSenderRequestId = ?1 AND fr.id.userToWhomId = ?2) " +
            "OR (fr.id.userSenderRequestId = ?2 AND fr.id.userToWhomId = ?1)")
    int deleteAllByContainingUsers(int firstIdUser, int secondIdUser);
}
