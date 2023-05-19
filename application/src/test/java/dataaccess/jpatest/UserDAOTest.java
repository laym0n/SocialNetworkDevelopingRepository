package dataaccess.jpatest;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import social.network.entities.UserEntity;
import social.network.dao.UserDAO;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOTest extends JPAIntegrationEnvironment {
    @Autowired
    private UserDAO SUT;
    @Autowired
    private EntityManager entityManager;
//    @Autowired
//    private TestClass user;

    @Test
    public void validAddTest() {
        //Assign
        OffsetDateTime now = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserEntity userForAdd = new UserEntity();
        userForAdd.setUserName("user");
        userForAdd.setPassword("password");
        userForAdd.setLastGetUpdatesTime(now);

        //Action
        SUT.save(userForAdd);
        UserEntity userForAd = new UserEntity();
        userForAd.setUserName("user");
        userForAd.setPassword("password");
        userForAd.setLastGetUpdatesTime(now);

        SUT.save(userForAd);
        entityManager.detach(userForAdd);

        //Assert
        Optional<UserEntity> resultFromSUT = SUT.findById(userForAdd.getId());
        assertTrue(resultFromSUT.isPresent(), () -> "User was saved but not founded");
        assertEquals(userForAdd, resultFromSUT.get());
    }
}
