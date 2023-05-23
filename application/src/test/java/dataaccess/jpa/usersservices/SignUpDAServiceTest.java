package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DuplicateKeyException;
import social.network.dao.UserDAO;
import social.network.daservices.SignUpDAService;
import social.network.entities.UserEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserInfo;
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
        byte[] avatar = new byte[] {1, 2, 3, 4};
        UserInfo userInfoForArgument = UserInfo.builder()
                .userName("laym0n")
                .firstName("Victor")
                .secondName("Kochnev")
                .avatar(Optional.of(avatar))
                .build();

        //Action
        SUT.createUserWithUserInfo(userForArgument, userInfoForArgument);

        //Assert
        Optional<UserEntity> resultFromDBById = userDAO.findById(userForArgument.getId());
        assertTrue(resultFromDBById.isPresent(), () -> "User with id " + userForArgument.getId() + " does not exist in db");
        UserEntity userEntityFromDB = resultFromDBById.get();

        assertEquals(userForArgument, userEntityFromDB.getUser());
        assertEquals(userInfoForArgument, userEntityFromDB.getUserInfo());
        Optional<UserEntity> resultFromDBByName = userDAO.findByUserName(userForArgument.getUserName());
        assertEquals(resultFromDBByName, resultFromDBById);
    }
    @Test
    @Rollback
    @Transactional
    public void signUpWithAlreadyExistedUserNameTest_ExpectedEntityAlreadyExistsException(){
        //Assign
        userDAO.save(UserEntity.builder()
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
        byte[] avatar = new byte[] {1, 2, 3, 4};
        UserInfo userInfoForArgument = UserInfo.builder()
                .userName("laym0n")
                .firstName("Victor")
                .secondName("Kochnev")
                .avatar(Optional.ofNullable(avatar))
                .build();

        //Action
        assertThrows(EntityAlreadyExistsException.class,
                () -> SUT.createUserWithUserInfo(userForArgument, userInfoForArgument));
    }
}
