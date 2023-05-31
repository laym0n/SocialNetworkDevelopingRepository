package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.usersrelationships.friends.FriendRequest;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;
import social.network.jpa.dao.FriendDAO;
import social.network.jpa.dao.FriendRequestDAO;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.dao.UsersBlackListDAO;
import social.network.jpa.entities.BlackListUserEntity;
import social.network.jpa.entities.FriendEntity;
import social.network.jpa.entities.FriendRequestEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.jpa.entities.ids.FriendEntityId;
import social.network.jpa.entities.ids.FriendRequestEntityId;
import social.network.jpa.implbllservices.usersservices.JPAManageFriendRequestsDAService;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ManageFriendRequestDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private JPAManageFriendRequestsDAService SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private FriendRequestDAO friendRequestDAO;
    @Autowired
    private UsersBlackListDAO usersBlackListDAO;
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
    public void testValidSaveRequest() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();

        //Action
        SUT.createFriendRequestAndCheckIfAlreadyExists(request);

        //Assert
        Optional<FriendRequestEntity> resultFromDB = friendRequestDAO.findById(
                new FriendRequestEntityId(request)
        );
        assertTrue(resultFromDB.isPresent(), () -> "Friend request do not find");
        assertEquals(request, resultFromDB.get().getFriendRequest());
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveAlreadyExistRequest_ExpectedDuplicatedKeyException() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();
        SUT.createFriendRequestAndCheckIfAlreadyExists(request);

        //Action
        assertThrows(EntityAlreadyExistsException.class, () -> SUT.createFriendRequestAndCheckIfAlreadyExists(request));
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveWhenAlreadyExistRequestFromOther_ExpectedDuplicatedKeyException() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest requestFromOther = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();
        SUT.createFriendRequestAndCheckIfAlreadyExists(requestFromOther);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();

        //Action
        assertThrows(EntityAlreadyExistsException.class, () -> SUT.createFriendRequestAndCheckIfAlreadyExists(request));
    }

    @Test
    @Transactional
    @Rollback
    public void testExistFriendRelationship_ExpectedTrue() {
        //Assign
        FriendEntity friendEntityForSave = FriendEntity.builder()
                .id(new FriendEntityId(firstUser.getId(), secondUser.getId()))
                .whenBecameFriends(OffsetDateTime.now())
                .build();
        friendDAO.create(friendEntityForSave);

        //Action
        boolean resultFromSUT = SUT.isExistFriendRelationship(firstUser.getId(), secondUser.getId());

        //Assert
        assertTrue(resultFromSUT);
    }

    @Test
    @Transactional
    @Rollback
    public void testExistFriendRelationshipWithSwappedIds_ExpectedTrue() {
        //Assign
        FriendEntity friendEntityForSave = FriendEntity.builder()
                .id(new FriendEntityId(secondUser.getId(), firstUser.getId()))
                .whenBecameFriends(OffsetDateTime.now())
                .build();
        friendDAO.create(friendEntityForSave);

        //Action
        boolean resultFromSUT = SUT.isExistFriendRelationship(firstUser.getId(), secondUser.getId());

        //Assert
        assertTrue(resultFromSUT);
    }

    @Test
    @Transactional
    @Rollback
    public void testExistFriendRelationship_ExpectedFalse() {
        //Assign

        //Action
        boolean resultFromSUT = SUT.isExistFriendRelationship(firstUser.getId(), secondUser.getId());

        //Assert
        assertFalse(resultFromSUT);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteSavedRequest() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();
        SUT.createFriendRequestAndCheckIfAlreadyExists(request);


        //Action
        SUT.deleteFriendRequest(request);

        //Assert
        Optional<FriendRequestEntity> resultFromDB = friendRequestDAO.findById(
                new FriendRequestEntityId(request)
        );
        assertFalse(resultFromDB.isPresent(), () -> "Friend request must be deleted");
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteNotSavedRequest() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();


        //Action
        SUT.deleteFriendRequest(request);

        //Assert
        Optional<FriendRequestEntity> resultFromDB = friendRequestDAO.findById(
                new FriendRequestEntityId(request)
        );
        assertFalse(resultFromDB.isPresent(), () -> "Friend request must be deleted");
    }

    @Test
    @Transactional
    @Rollback
    public void testUsersInBlackList_ExpectedTrue() {
        //Assign
        BlackListUserEntity blackListUserEntity = BlackListUserEntity
                .builder()
                .id(new BlackListUserEntityId(firstUser.getId(), secondUser.getId()))
                .whenBlocked(OffsetDateTime.now())
                .build();
        usersBlackListDAO.create(blackListUserEntity);

        //Action
        boolean resultFromSUT = SUT.isBlackListRelationshipExistBetweenUsers(firstUser.getId(), secondUser.getId());

        //Assert
        assertTrue(resultFromSUT);
    }

    @Test
    @Transactional
    @Rollback
    public void testUsersInBlackList_ExpectedFalse() {
        //Action
        boolean resultFromSUT = SUT.isBlackListRelationshipExistBetweenUsers(firstUser.getId(), secondUser.getId());

        //Assert
        assertFalse(resultFromSUT);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteSavedRequestAndCreateFriend() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();
        SUT.createFriendRequestAndCheckIfAlreadyExists(request);

        //Action
        SUT.checkAndDeleteFriendRequestAndCreateFriend(request);

        //Assert
        Optional<FriendRequestEntity> requestFromDB = friendRequestDAO.findById(
                new FriendRequestEntityId(request)
        );
        assertFalse(requestFromDB.isPresent(), () -> "Friend request must be deleted");

        Optional<FriendEntity> friendsFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertTrue(friendsFromDB.isPresent(), () -> "Friend must be found");
        FriendEntity friendEntityFromDB = friendsFromDB.get();
        friendEntityFromDB.setWhenBecameFriends(
                friendEntityFromDB.getWhenBecameFriends().truncatedTo(ChronoUnit.MINUTES)
        );
        assertTrue(
                friendEntityFromDB.getId().equals(new FriendEntityId(firstUser.getId(), secondUser.getId()))
                        ^ friendEntityFromDB.getId().equals(new FriendEntityId(secondUser.getId(), firstUser.getId())),
                () -> "Ids not equal in friends"
        );
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteNotSavedRequestAndCreateFriend_ExpectedEntityNotFoundException() {
        //Assign
        OffsetDateTime whenSent = OffsetDateTime.now().minusDays(5);
        FriendRequest request = FriendRequest.builder()
                .idUserSender(firstUser.getId())
                .idUserToWhom(secondUser.getId())
                .whenSent(whenSent)
                .build();

        //Action
        assertThrows(EntityNotFoundException.class, () -> SUT.checkAndDeleteFriendRequestAndCreateFriend(request));

        //Assert
        Optional<FriendRequestEntity> requestFromDB = friendRequestDAO.findById(
                new FriendRequestEntityId(request)
        );
        assertFalse(requestFromDB.isPresent(), () -> "Friend request must not be create");

        Optional<FriendEntity> friendsFromDB =
                friendDAO.findFriendEntityWithUsers(firstUser.getId(), secondUser.getId());
        assertFalse(friendsFromDB.isPresent(), () -> "Friend must not be create");
    }
}
