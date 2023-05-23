package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.FriendDAO;
import social.network.dao.FriendRequestDAO;
import social.network.dao.UserDAO;
import social.network.dao.UsersBlackListDAO;
import social.network.entities.FriendEntity;
import social.network.entities.FriendRequestEntity;
import social.network.entities.UserEntity;
import social.network.entities.ids.FriendEntityId;
import social.network.entities.ids.FriendRequestEntityId;
import social.network.implbllservices.usersservices.FriendRelationshipsHandlerDAServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import java.time.OffsetDateTime;
import java.util.Optional;

public class FriendRelationshipsHandlerDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private FriendRelationshipsHandlerDAServiceImpl SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private FriendRequestDAO friendRequestDAO;
    @Autowired
    private UsersBlackListDAO usersBlackListDAO;
    @PersistenceContext
    private EntityManager entityManager;
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
        firstUser = userDAO.save(firstUser);
        secondUser = UserEntity.builder()
                .userName("second")
                .password("password")
                .firstName("firstName")
                .secondName("secondName")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .isBlocked(false)
                .build();
        secondUser = userDAO.save(secondUser);
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
        friendDAO.save(friendEntityForSave);

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
    public void testValidRefdgmoveFriendRequest() {
        //Assign
        FriendRequestEntity friendRequestEntityForSave = FriendRequestEntity
                .builder()
                .whenRequestSent(OffsetDateTime.now())
                .id(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()))
                .build();
        friendRequestDAO.save(friendRequestEntityForSave);
        Optional<FriendRequestEntity> friesdfndRequestFromDB =
                friendRequestDAO.findById(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()));
        assertTrue(friesdfndRequestFromDB.isPresent());
        entityManager.detach(friendRequestEntityForSave);

        //Action
        int count = friendRequestDAO.deleteAllByContainingUsers(secondUser.getId(), firstUser.getId());

        //Assert
        assertEquals(1, count);
        Optional<FriendEntity> friendFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertTrue(friendFromDB.isEmpty(), () -> "Friend relationship must not exist in db");
        Optional<FriendRequestEntity> friendRequestFromDB =
                friendRequestDAO.findById(new FriendRequestEntityId(firstUser.getId(), secondUser.getId()));
        entityManager.detach(friendRequestFromDB.get());
        friendRequestFromDB =
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
        friendRequestDAO.save(friendRequestEntityForSave);
        FriendEntity friendEntityForSave = FriendEntity
                .builder()
                .whenBecameFriends(OffsetDateTime.now())
                .id(new FriendEntityId(firstUser.getId(), secondUser.getId()))
                .build();
        friendDAO.save(friendEntityForSave);

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
