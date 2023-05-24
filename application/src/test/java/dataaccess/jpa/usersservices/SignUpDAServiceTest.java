package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.user.PersonalInfo;
import social.network.jpa.dao.UserDAO;
import social.network.daservices.SignUpDAService;
import social.network.jpa.entities.UserEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserRole;
import social.network.exceptions.EntityAlreadyExistsException;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SignUpDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private SignUpDAService SUT;
    @Autowired
    private UserDAO userDAO;
    @Test
    @Rollback
    @Transactional
    public void validSignUpTest(){
        //Assign
        OffsetDateTime lastGetUpdate = OffsetDateTime.now().minusDays(5);
        Map<Integer, UserRole> roles = new HashMap<>();
        roles.put(UserRole.SIMPLE_USER.getIdGroup(), UserRole.SIMPLE_USER);
        User userForArgument = User.builder()
                .isBlocked(false)
                .userName("laym0n")
                .password("password")
                .lastGetUpdatesTime(lastGetUpdate)
                .roles(roles)
                .build();
        PersonalInfo userInfoForArgument = PersonalInfo.builder()
                .firstName("Victor")
                .secondName("Kochnev")
                .birthday(Optional.ofNullable(null))
                .build();

        //Action
        SUT.createUserWithPersonalInfo(userForArgument, userInfoForArgument);

        //Assert
        Optional<UserEntity> resultFromDBById = userDAO.findById(userForArgument.getId());
        assertTrue(resultFromDBById.isPresent(), () -> "User with id " + userForArgument.getId() + " does not exist in db");
        UserEntity userEntityFromDB = resultFromDBById.get();

        assertEquals(userForArgument, userEntityFromDB.getUser());
        assertEquals(userInfoForArgument, userEntityFromDB.getPersonalInfo());
        Optional<UserEntity> resultFromDBByName = userDAO.findByUserName(userForArgument.getUserName());
        assertEquals(resultFromDBByName, resultFromDBById);
    }
    @Test
    @Rollback
    @Transactional
    public void signUpWithAlreadyExistedUserNameTest_ExpectedEntityAlreadyExistsException(){
        //Assign
        userDAO.create(UserEntity.builder()
                .isBlocked(false)
                .userName("laym0n")
                .password("password")
                .firstName("Victor")
                .secondName("Kochnev")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .build()
        );

        OffsetDateTime lastGetUpdate = OffsetDateTime.now().minusDays(5);
        Map<Integer, UserRole> roles = new HashMap<>();
        roles.put(UserRole.SIMPLE_USER.getIdGroup(), UserRole.SIMPLE_USER);
        User userForArgument = User.builder()
                .isBlocked(false)
                .userName("laym0n")
                .password("password")
                .lastGetUpdatesTime(lastGetUpdate)
                .roles(roles)
                .build();
        PersonalInfo userInfoForArgument = PersonalInfo.builder()
                .firstName("Victor")
                .secondName("Kochnev")
                .birthday(Optional.ofNullable(null))
                .build();

        //Action
        assertThrows(EntityAlreadyExistsException.class,
                () -> SUT.createUserWithPersonalInfo(userForArgument, userInfoForArgument));
    }
}
