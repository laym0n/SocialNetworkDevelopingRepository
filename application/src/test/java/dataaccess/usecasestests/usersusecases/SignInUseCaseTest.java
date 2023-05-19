package dataaccess.usecasestests.usersusecases;

import dataaccess.jpatest.JPAIntegrationEnvironment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.UserRoleDAO;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.dto.requests.SignUpRequest;
import social.network.dto.responses.SignUpResponse;
import social.network.entities.UserRoleEntity;
import social.network.entities.user.User;
import social.network.entities.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import social.network.dao.UserDAO;
import social.network.entities.UserEntity;
import social.network.usecases.usersusecases.SignInUseCase;
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SignInUseCaseTest extends JPAIntegrationEnvironment {
    @Autowired
    private SignInUseCase SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Test
    @Transactional
    @Rollback
    public void validSignUpTest(){
        //Assign
        Map<Integer, UserRole> roles = new HashMap<>();
        roles.put(UserRole.SIMPLE_USER.getIdGroup(), UserRole.SIMPLE_USER);
        byte[] avatar = new byte[] {1, 2, 3, 4};
        SignUpRequest request = new SignUpRequest(
                "user",
                "pass",
                new UserInfoDTO("Виктор", "Кочнев", avatar)
        );

        //Action
        SUT.signUp(request);

        //Assert
        Optional<UserEntity> optionalResultFromDB = userDAO.findByUserName("user");
        assertTrue(optionalResultFromDB.isPresent(), () -> "User with username user not found ");
        UserEntity resultFromDB = optionalResultFromDB.get();
        assertFalse(resultFromDB.isBlocked());
        assertEquals("user", resultFromDB.getUserName());
        assertEquals("pass", resultFromDB.getPassword());
        assertEquals("Виктор", resultFromDB.getFirstName());
        assertEquals("Кочнев", resultFromDB.getSecondName());
        assertEquals(OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                resultFromDB.getLastGetUpdatesTime().truncatedTo(ChronoUnit.MINUTES));
        assertTrue(Arrays.equals(avatar, resultFromDB.getAvatar()));
        List<UserRoleEntity> expectedRoles = List.of(
                new UserRoleEntity(0, "SIMPLE_USER")
        );
        assertEquals(expectedRoles, resultFromDB.getRoles());
    }
    @Test
    @Transactional
    @Rollback
    public void notValidSignUpTest_ExpectedDuplicatedKeyException(){
        //Assign
        List<UserRoleEntity> roles = List.of(
                userRoleDAO.findByName("SIMPLE_USER")
        );
        byte[] avatar = new byte[] {1, 2, 3, 4};
        UserEntity savedUser = UserEntity.builder()
                .userName("user")
                .password("pass")
                .firstName("Виктор")
                .secondName("Кочнев")
                .roles(roles)
                .lastGetUpdatesTime(OffsetDateTime.now())
                .isBlocked(false)
                .build();
        userDAO.save(savedUser);

        SignUpRequest request = new SignUpRequest(
                "user",
                "pass",
                new UserInfoDTO("Виктор", "Кочнев", avatar)
        );

        //Action
        assertThrows(DuplicateKeyException.class, () -> SUT.signUp(request));
    }
}
