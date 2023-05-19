package dataaccess.usecasestests.usersusecases;

import dataaccess.jpatest.JPAIntegrationEnvironment;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.UserDAO;
import social.network.dao.UserRoleDAO;
import social.network.dto.modelsdto.UserInfoDTO;
import social.network.dto.requests.UpdateUserProfileRequest;
import social.network.entities.UserEntity;
import social.network.entities.UserRoleEntity;
import social.network.usecases.usersusecases.EditUserProfileUseCase;
import static org.junit.jupiter.api.Assertions.*;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class EditUserProfileUseCaseTest extends JPAIntegrationEnvironment {
    @Autowired
    EditUserProfileUseCase SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Test
    @Transactional
    @Rollback
    void validUpdateProfile(){
        //Assign
        byte[] avatar = new byte[] {1, 2, 3, 4};
        List<UserRoleEntity> roles = List.of(
                userRoleDAO.findByName("SIMPLE_USER")
        );
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
        entityManager.detach(savedUser);
        OffsetDateTime newBirthday = OffsetDateTime.now().minusDays(5).truncatedTo(ChronoUnit.MINUTES);
        UpdateUserProfileRequest request = new UpdateUserProfileRequest(savedUser.getId(), newBirthday,
                new UserInfoDTO("Виктор1", "Кочнев1", null));

        //Action
        SUT.updateUserProfile(request);

        //Assert
        Optional<UserEntity> resultFromSUT = userDAO.findById(savedUser.getId());
        assertTrue(resultFromSUT.isPresent(), ()-> "User must be found");

        assertEquals("Виктор1", resultFromSUT.get().getFirstName());
        assertEquals("Кочнев1", resultFromSUT.get().getSecondName());
        assertEquals(null, resultFromSUT.get().getAvatar());
        assertFalse(resultFromSUT.get().isBlocked());
        assertEquals("user", resultFromSUT.get().getUserName());
        assertEquals("pass", resultFromSUT.get().getPassword());
        assertEquals(newBirthday, resultFromSUT.get().getBirthday().truncatedTo(ChronoUnit.MINUTES));
    }
}
