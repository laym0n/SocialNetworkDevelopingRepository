package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.FriendRequestDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.FriendRequestEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.jpa.implbllservices.usersservices.JPAFriendRelationshipsHandlerDAService;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendRelationshipsHandlerDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private JPAFriendRelationshipsHandlerDAService SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private FriendRequestDAO friendRequestDAO;
    private UserEntity firstUser;
    private UserEntity secondUser;

    @BeforeEach
    public void saveUsers() {
        firstUser = UserEntity.builder()
                .userName("first")
                .password("password")
                .firstName("firstName")
                .secondName("secondName")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .isBlocked(false)
                .build();
        firstUser = userDAO.create(firstUser);
        secondUser = UserEntity.builder()
                .userName("second")
                .password("password")
                .firstName("firstName")
                .secondName("secondName")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .isBlocked(false)
                .build();
        secondUser = userDAO.create(secondUser);
    }

    @Test
    @Transactional
    @Rollback
    public void testValidRemoveFriendRelationship() {
        //Assign
        FriendEntity friendEntityForSave = FriendEntity
                .builder()
                .whenBecameFriends(OffsetDateTime.now())
                .id(new FriendEntityId(firstUser.getId(), secondUser.getId()))
                .build();
        friendDAO.create(friendEntityForSave);

        //Action
        SUT.deleteFriendRelationshipAndFriendRequestIfExist(firstUser.getId(), secondUser.getId());

        //Assert
        Optional<FriendEntity> resultFromDB = friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertTrue(resultFromDB.isEmpty(), () -> "Friend relationship must not exist in db");
        Optional<FriendRequestEntity> friendRequestFromDB =
                friendRequestDAO.findById(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()));
        assertTrue(friendRequestFromDB.isEmpty(), () -> "Friend request must not exist in db");
    }

    @Test
    @Transactional
    @Rollback
    public void testValidRemoveNotExistedFriendRelationshipAndRequest() {
        //Action
        SUT.deleteFriendRelationshipAndFriendRequestIfExist(firstUser.getId(), secondUser.getId());

        //Assert
        Optional<FriendEntity> resultFromDB = friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertTrue(resultFromDB.isEmpty(), () -> "Friend relationship must not exist in db");
        Optional<FriendRequestEntity> friendRequestFromDB =
                friendRequestDAO.findById(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()));
        assertTrue(friendRequestFromDB.isEmpty(), () -> "Friend request must not exist in db");
    }

    @Test
    @Transactional
    @Rollback
    public void testValidRemoveFriendRelationshipAndFriendRequest() {
        //Assign
        FriendRequestEntity friendRequestEntityForSave = FriendRequestEntity
                .builder()
                .whenRequestSent(OffsetDateTime.now())
                .id(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()))
                .build();
        friendRequestDAO.create(friendRequestEntityForSave);
        FriendEntity friendEntityForSave = FriendEntity
                .builder()
                .whenBecameFriends(OffsetDateTime.now())
                .id(new FriendEntityId(firstUser.getId(), secondUser.getId()))
                .build();
        friendDAO.create(friendEntityForSave);

        //Action
        SUT.deleteFriendRelationshipAndFriendRequestIfExist(firstUser.getId(), secondUser.getId());

        //Assert
        Optional<FriendEntity> friendFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertTrue(friendFromDB.isEmpty(), () -> "Friend relationship must not exist in db");
        Optional<FriendRequestEntity> friendRequestFromDB =
                friendRequestDAO.findById(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()));
        assertTrue(friendRequestFromDB.isEmpty(), () -> "Friend request must not exist in db");
    }

}
