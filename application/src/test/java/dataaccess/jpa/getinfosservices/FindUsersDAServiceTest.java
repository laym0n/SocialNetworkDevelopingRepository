package dataaccess.jpa.getinfosservices;

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
import social.network.daservices.FindUsersDAService;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.entities.UserRoleEntity;
import social.network.entities.user.UserInfo;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindUsersDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private FindUsersDAService SUT;
    @Autowired
    private JPAUserDAO JPAUserDAO;
    @Autowired
    private JPAUserRoleDAO JPAUserRoleDAO;
    @ParameterizedTest
    @ArgumentsSource(FindUsersArguments.class)
    @Transactional
    @Rollback
    public void testFindUsers(String searchString, List<UserEntity> usersForSave, List<UserEntity> expectedInfos) {
        //Assign
        saveAllUsers(usersForSave);
        saveAllUsers(expectedInfos);
        for (UserEntity userEntity : expectedInfos) {
            assertTrue((userEntity.getFirstName() + " " + userEntity.getSecondName()).contains(searchString));
        }

        //Action
//        List<UserInfo> resultFromSUT = SUT.findUsersWithFirstNameAndSecondNameContainingSearchString(searchString);

        //Assert
//        Set<UserInfo> resultFromSUTSet = new HashSet<>(resultFromSUT);
//        Set<UserInfo> expectedInfosSet = expectedInfos.stream()
//                .map(UserEntity::getUserInfo)
//                .collect(Collectors.toSet());
//        assertEquals(expectedInfosSet, resultFromSUTSet);
    }
    private void saveAllUsers(List<UserEntity> usersForSave) {
        UserRoleEntity simpleUser = JPAUserRoleDAO.findByName("SIMPLE_USER");
        for (UserEntity userEntity : usersForSave){
            userEntity.getRoles().add(simpleUser);
            JPAUserDAO.save(userEntity);
        }
    }
    static class FindUsersArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments firstArguments;
            {
                firstArguments = Arguments.of(
                        "Vic",
                        List.of(

                        ),
                        List.of(
                                UserEntity.builder()
                                        .firstName("Victor")
                                        .secondName("Kochnev")
                                        .userName("laym0n")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Vic")
                                        .secondName("Kochnev")
                                        .userName("laym0n1")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build()
                        )
                );
            }
            Arguments secondArguments;
            {
                secondArguments = Arguments.of(
                        "Vic",
                        List.of(
                                UserEntity.builder()
                                        .firstName("Laym0n")
                                        .secondName("Kochnev")
                                        .userName("laym0n2")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build()
                        ),
                        List.of(
                                UserEntity.builder()
                                        .firstName("Victor")
                                        .secondName("Kochnev")
                                        .userName("laym0n")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Vic")
                                        .secondName("Kochnev")
                                        .userName("laym0n1")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build()
                        )
                );
            }
            Arguments thirdArguments;
            {
                thirdArguments = Arguments.of(
                        "Vic",
                        List.of(
                        ),
                        List.of(
                                UserEntity.builder()
                                        .firstName("Victor")
                                        .secondName("Kochnev")
                                        .userName("laym0n")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Vic")
                                        .secondName("Kochnev")
                                        .userName("laym0n1")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Laym0n")
                                        .secondName("Vichnev")
                                        .userName("laym0n2")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build()
                        )
                );
            }
            Arguments fourdArguments;
            {
                fourdArguments = Arguments.of(
                        "OtherText",
                        List.of(
                                UserEntity.builder()
                                        .firstName("Victor")
                                        .secondName("Kochnev")
                                        .userName("laym0n")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Vic")
                                        .secondName("Kochnev")
                                        .userName("laym0n1")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build(),
                                UserEntity.builder()
                                        .firstName("Laym0n")
                                        .secondName("Vichnev")
                                        .userName("laym0n2")
                                        .birthday(LocalDate.now().minusDays(5))
                                        .password("password")
                                        .lastGetUpdatesTime(OffsetDateTime.now())
                                        .build()
                        ),
                        List.of(
                        )
                );
            }
            return Stream.of(
                    firstArguments,
                    secondArguments,
                    thirdArguments,
                    fourdArguments
            );
        }
    }
}
