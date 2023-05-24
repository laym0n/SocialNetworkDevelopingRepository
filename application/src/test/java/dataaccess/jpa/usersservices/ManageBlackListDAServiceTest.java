package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.dao.UsersBlackListDAO;
import social.network.jpa.entities.BlackListUserEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.ids.BlackListUserEntityId;
import social.network.entities.usersrelationships.blacklist.BlackListRelationship;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.EntityNotFoundException;
import social.network.jpa.implbllservices.usersservices.JPAManageBlackListDAService;

import static org.junit.jupiter.api.Assertions.*;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class ManageBlackListDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private JPAManageBlackListDAService SUT;
    @Autowired
    private UsersBlackListDAO usersBlackListDAO;
    @Autowired
    private UserDAO userDAO;
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
    public void validAddToBlackList(){
        //Assign
        OffsetDateTime whenAdded = OffsetDateTime.now().minusDays(5);
        BlackListRelationship blackListRelationshipForSave = BlackListRelationship
                .builder()
                .idOwnerBlackList(firstUser.getId())
                .idBlockedUser(secondUser.getId())
                .whenAdded(whenAdded)
                .build();

        //Action
        SUT.saveBlackListRelationshipAndCheckAlreadyExists(blackListRelationshipForSave);

        //Assert
        Optional<BlackListUserEntity> resultFromDB = usersBlackListDAO.findById(
                new BlackListUserEntityId(
                        firstUser.getId(),
                        secondUser.getId()
                )
        );
        assertTrue(resultFromDB.isPresent(), () -> "BlackListUserEntity not founded");

        BlackListUserEntity entityFromDB = resultFromDB.get();
        entityFromDB.setWhenBlocked(entityFromDB.getWhenBlocked().truncatedTo(ChronoUnit.MINUTES));
        blackListRelationshipForSave.setWhenAdded(whenAdded.truncatedTo(ChronoUnit.MINUTES));
        assertEquals(entityFromDB, new BlackListUserEntity(blackListRelationshipForSave));
    }
    @Test
    @Transactional
    @Rollback
    public void addToBlackListUserThatAlreadyInList_ExpectedEntityAlreadyExistsException(){
        //Assign
        OffsetDateTime whenAdded = OffsetDateTime.now().minusDays(5);
        BlackListRelationship blackListRelationshipForSave = BlackListRelationship
                .builder()
                .idOwnerBlackList(firstUser.getId())
                .idBlockedUser(secondUser.getId())
                .whenAdded(whenAdded)
                .build();
        SUT.saveBlackListRelationshipAndCheckAlreadyExists(blackListRelationshipForSave);

        //Action
        assertThrows(EntityAlreadyExistsException.class,
                () -> SUT.saveBlackListRelationshipAndCheckAlreadyExists(blackListRelationshipForSave));

        //Assert
        Optional<BlackListUserEntity> resultFromDB = usersBlackListDAO.findById(
                new BlackListUserEntityId(
                        firstUser.getId(),
                        secondUser.getId()
                )
        );
        assertTrue(resultFromDB.isPresent(), () -> "BlackListUserEntity not founded");

        BlackListUserEntity entityFromDB = resultFromDB.get();
        entityFromDB.setWhenBlocked(entityFromDB.getWhenBlocked().truncatedTo(ChronoUnit.MINUTES));
        blackListRelationshipForSave.setWhenAdded(whenAdded.truncatedTo(ChronoUnit.MINUTES));
        assertEquals(entityFromDB, new BlackListUserEntity(blackListRelationshipForSave));
    }
    @Test
    @Transactional
    @Rollback
    public void validRemoveFromBlackList(){
        //Assign
        OffsetDateTime whenAdded = OffsetDateTime.now().minusDays(5);
        BlackListRelationship blackListRelationshipForSave = BlackListRelationship
                .builder()
                .idOwnerBlackList(firstUser.getId())
                .idBlockedUser(secondUser.getId())
                .whenAdded(whenAdded)
                .build();
        SUT.saveBlackListRelationshipAndCheckAlreadyExists(blackListRelationshipForSave);

        //Action
        SUT.deleteBlackListRelationship(blackListRelationshipForSave);

        //Assert
        Optional<BlackListUserEntity> resultFromDB = usersBlackListDAO.findById(
                new BlackListUserEntityId(
                        firstUser.getId(),
                        secondUser.getId()
                )
        );
        assertTrue(resultFromDB.isEmpty(), () -> "BlackListUserEntity must not be found");
    }
    @Test
    @Transactional
    @Rollback
    public void removeFromBlackListUserThatIsNotInList_ExpectedEntityNotFoundException(){
        //Assign
        OffsetDateTime whenAdded = OffsetDateTime.now().minusDays(5);
        BlackListRelationship blackListRelationshipForSave = BlackListRelationship
                .builder()
                .idOwnerBlackList(firstUser.getId())
                .idBlockedUser(secondUser.getId())
                .whenAdded(whenAdded)
                .build();

        //Action
        assertThrows(EntityNotFoundException.class,
                () -> SUT.deleteBlackListRelationship(blackListRelationshipForSave));

        //Assert
        Optional<BlackListUserEntity> resultFromDB = usersBlackListDAO.findById(
                new BlackListUserEntityId(
                        firstUser.getId(),
                        secondUser.getId()
                )
        );
        assertTrue(resultFromDB.isEmpty(), () -> "BlackListUserEntity must not be found");
    }
}
