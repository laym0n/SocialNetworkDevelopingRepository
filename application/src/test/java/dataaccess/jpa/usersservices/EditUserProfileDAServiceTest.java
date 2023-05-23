package dataaccess.jpa.usersservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.UserDAO;
import social.network.entities.UserEntity;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;
import social.network.implbllservices.usersservices.EditUserProfileDAServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EditUserProfileDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private EditUserProfileDAServiceImpl SUT;
    @Autowired
    private UserDAO userDAO;

    @ParameterizedTest
    @ArgumentsSource(ValidUpdateProfileArguments.class)
    @Transactional
    @Rollback
    void testValidUpdateProfile(UserEntity userForSave, UserProfile userProfileForUpdate, UserEntity expectedEntity){
        //Assign
        userForSave = userDAO.save(userForSave);
        expectedEntity.setId(userForSave.getId());
        userProfileForUpdate.getOwner().setIdUser(userForSave.getId());

        //Action
        SUT.updateUserProfileWithoutAvatar(userProfileForUpdate);

        //Assert
        Optional<UserEntity> resultFromDB = userDAO.findById(userForSave.getId());
        assertTrue(resultFromDB.isPresent(), () -> "User must be found");
        assertEquals(expectedEntity, resultFromDB.get());
    }

    @ParameterizedTest
    @ArgumentsSource(ValidUpdateAvatarArguments.class)
    @Transactional
    @Rollback
    void testValidUpdateAvatar(UserEntity userForSave, byte[] newAvatar, UserEntity expectedEntity){
        //Assign
        userForSave = userDAO.save(userForSave);
        expectedEntity.setId(userForSave.getId());

        //Action
        SUT.updateAvatarUser(userForSave.getId(), newAvatar);

        //Assert
        Optional<UserEntity> resultFromDB = userDAO.findById(userForSave.getId());
        assertTrue(resultFromDB.isPresent(), () -> "User must be found");
        assertEquals(expectedEntity, resultFromDB.get());
    }
    static class ValidUpdateProfileArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments firstArguments;
            {
                OffsetDateTime lastGetUpdateTime = OffsetDateTime.now().minusDays(5);
                LocalDate newBirthday = LocalDate.now().minusDays(5);
                firstArguments = Arguments.of(
                        UserEntity
                                .builder()
                                .userName("userName")
                                .password("password")
                                .firstName("firstName")
                                .secondName("secondName")
                                .lastGetUpdatesTime(lastGetUpdateTime)
                                .birthday(null)
                                .avatar(new byte[] {1, 2, 3, 4})
                                .build(),
                        UserProfile
                                .builder()
                                .birthday(Optional.of(newBirthday))
                                .profileNewsFeed(null)
                                .owner(
                                        UserInfo
                                                .builder()
                                                .userName("userName")
                                                .firstName("firstName1")
                                                .secondName("secondName1")
                                                .avatar(Optional.of(new byte[] { 1 }))
                                                .lastGetUpdate(OffsetDateTime.now())
                                                .build()
                                )
                                .build(),
                        UserEntity
                                .builder()
                                .userName("userName")
                                .password("password")
                                .firstName("firstName1")
                                .secondName("secondName1")
                                .lastGetUpdatesTime(lastGetUpdateTime)
                                .birthday(newBirthday)
                                .avatar(new byte[] {1, 2, 3, 4})
                                .build()
                );
            }
            return Stream.of(
                    firstArguments
            );
        }
    }
    static class ValidUpdateAvatarArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments firstArguments;
            {
                OffsetDateTime lastGetUpdateTime = OffsetDateTime.now().minusDays(5);
                LocalDate newBirthday = LocalDate.now().minusDays(5);
                firstArguments = Arguments.of(
                        UserEntity
                                .builder()
                                .userName("userName")
                                .password("password")
                                .firstName("firstName")
                                .secondName("secondName")
                                .lastGetUpdatesTime(lastGetUpdateTime)
                                .birthday(null)
                                .avatar(new byte[] {1, 2, 3, 4})
                                .build(),
                        new byte[] { 1 },
                        UserEntity
                                .builder()
                                .userName("userName")
                                .password("password")
                                .firstName("firstName")
                                .secondName("secondName")
                                .lastGetUpdatesTime(lastGetUpdateTime)
                                .birthday(null)
                                .avatar(new byte[] {1})
                                .build()
                );
            }
            return Stream.of(
                    firstArguments
            );
        }
    }

}
