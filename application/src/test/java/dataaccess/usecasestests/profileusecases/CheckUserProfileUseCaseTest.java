package dataaccess.usecasestests.profileusecases;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.jpadao.JPAUserDAO;
import social.network.jpa.jpadao.JPAUserRoleDAO;
import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.UserRoleEntity;
import social.network.usecases.getinfosusecases.CheckUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckUserProfileUseCaseTest extends JPAIntegrationEnvironment {
    @Autowired
    private CheckUserProfileUseCase SUT;
    @Autowired
    private JPAUserDAO JPAUserDAO;
    @Autowired
    private JPAUserRoleDAO JPAUserRoleDAO;
    @ParameterizedTest
    @ArgumentsSource(ValidArgumentsProviderForLoadProfileForOwner.class)
    @Transactional
    @Rollback
    public void testValidLoadProfileForOwnerProfile(UserEntity userEntityForSave, CheckUserProfileResponse expectedResult)
            throws AccountNotFoundException {
//        //Assign
//        UserRoleEntity simpleUser = JPAUserRoleDAO.findByName("SIMPLE_USER");
//        userEntityForSave.getRoles().add(simpleUser);
//        JPAUserDAO.save(userEntityForSave);
//        expectedResult.setIdUser(userEntityForSave.getId());
//        CheckUserProfileRequest request = new CheckUserProfileRequest(userEntityForSave.getId(), userEntityForSave.getId(), 5);
//
//        //Action
//        CheckUserProfileResponse resultFromSUT = SUT.checkUserProfile(request);
//
//        //Assert
//        assertEquals(expectedResult, resultFromSUT);
    }
    static class ValidArgumentsProviderForLoadProfileForOwner implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments firstArguments;
            {
                firstArguments = Arguments.of(
                        UserEntity.builder()
                            .firstName("Victor")
                            .secondName("Kochnev")
                            .userName("laym0n")
                            .birthday(LocalDate.now().minusDays(5))
                            .password("password")
                            .lastGetUpdatesTime(OffsetDateTime.now())
                            .build(),
                        CheckUserProfileResponse.builder()
//                                .firstName("Victor")
//                                .secondName("Kochnev")
//                                .userName("laym0n")
//                                .birthday(LocalDate.now().minusDays(5))
                                .build()
                );
            }
            Arguments secondArguments;
            {
                secondArguments = Arguments.of(
                        UserEntity.builder()
                                .firstName("Victor")
                                .secondName("Kochnev")
                                .userName("laym0n")
                                .birthday(LocalDate.now().minusDays(5))
                                .password("password")
                                .avatar(new byte[] {1, 2, 3, 4,5})
                                .lastGetUpdatesTime(OffsetDateTime.now())
                                .build(),
                        CheckUserProfileResponse.builder()
//                                .firstName("Victor")
//                                .secondName("Kochnev")
//                                .userName("laym0n")
//                                .avatar(new byte[] {1, 2, 3, 4,5})
//                                .birthday(LocalDate.now().minusDays(5))
                                .build()
                );
            }
            return Stream.of(
                    firstArguments,
                    secondArguments
            );
        }
    }
}
