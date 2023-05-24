package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import social.network.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.implbllservices.usersservices.JPAManageFriendsDAService;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ManageFriendsDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private JPAManageFriendsDAService SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendDAO friendDAO;
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
    @Rollback
    @Transactional
    public void testValidDeleteFriendRelationship(){
        //Assign
        FriendEntity friendEntityForSave = FriendEntity.builder()
                .id(new FriendEntityId(secondUser.getId(), firstUser.getId()))
                .whenBecameFriends(OffsetDateTime.now())
                .build();
        friendDAO.create(friendEntityForSave);

        //Action
        SUT.deleteFriendRelationShipWithUsers(secondUser.getId(), firstUser.getId());

        //Assert
        Optional<FriendEntity> friendsFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertFalse(friendsFromDB.isPresent(), () -> "Friend relationship must be deleted");
    }
    @Test
    @Rollback
    @Transactional
    public void testDeleteNotExistedFriendRelationship(){
        //Action
        assertThrows(EntityNotFoundException.class,
                () -> SUT.deleteFriendRelationShipWithUsers(secondUser.getId(), firstUser.getId()));

        //Assert
        Optional<FriendEntity> friendsFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertFalse(friendsFromDB.isPresent(), () -> "Friend relationship must be deleted");
    }
}
