package dataaccess.usecasestests.chatsusecases;

import dataaccess.jpatest.JPAIntegrationEnvironment;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.dao.ChatTypeDAO;
import social.network.dao.PrivateChatDAO;
import social.network.dao.UserDAO;
import social.network.dao.UserRoleDAO;
import social.network.dto.modelsdto.PrivateChatInfoDTO;
import social.network.dto.requests.CreatePrivateChatRequest;
import social.network.dto.requests.DeletePrivateChatRequest;
import social.network.dto.requests.EditPrivateChatRequest;
import social.network.entities.ChatTypeEntity;
import social.network.entities.PrivateChatEntity;
import social.network.entities.UserEntity;
import social.network.entities.UserRoleEntity;
import social.network.usecases.chatsusecases.ManagePrivateChatsUseCase;
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class ManagePrivateChatTest extends JPAIntegrationEnvironment {
    @Autowired
    private ManagePrivateChatsUseCase SUT;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PrivateChatDAO privateChatDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private ChatTypeDAO chatTypeDAO;
    @Autowired
    private EntityManager entityManager;
    private UserEntity savedUser;

    @BeforeEach
    public void saveUser(){
        savedUser = UserEntity.builder()
                .userName("user")
                .password("pass")
                .roles(List.of(
                        userRoleDAO.findByName("SIMPLE_USER")
                ))
                .isBlocked(false)
                .firstName("Виктор")
                .secondName("Кочнев")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .build();
        userDAO.save(savedUser);
    }

    @Test
    @Transactional
    @Rollback
    void validCreatePrivateChat() {
        //Assert

        CreatePrivateChatRequest request = CreatePrivateChatRequest.builder()
                .idUserSenderRequest(savedUser.getId())
                .privateChatInfoDTO(new PrivateChatInfoDTO("TestName"))
                .build();

        //Action
        SUT.createPrivateChat(request);

        //Assert
        Optional<PrivateChatEntity> resultFromDB = privateChatDAO.findByUserIdAndName(savedUser.getId(), "TestName");
        assertTrue(resultFromDB.isPresent(), () -> "Private Chat not founded");
        assertEquals("TestName", resultFromDB.get().getName());
        assertEquals(savedUser.getId(), resultFromDB.get().getUserId());
        assertEquals("PRIVATE_CHAT", resultFromDB.get().getType().getName());
    }

    @Test
    @Transactional
    @Rollback
    void validRemovePrivateChat() {
        //Assert
        PrivateChatEntity privateChat = PrivateChatEntity.builder()
                .userId(savedUser.getId())
                .name("TestName")
                .build();
        privateChat.setType(new ChatTypeEntity(chatTypeDAO.findIdByName("PRIVATE_CHAT"), "PRIVATE_CHAT"));
        privateChatDAO.save(privateChat);
        DeletePrivateChatRequest requestForDelete = new DeletePrivateChatRequest(
                savedUser.getId(),
                privateChat.getUserId()
        );

        //Action
        SUT.deletePrivateChat(requestForDelete);

        //Assert
        Optional<PrivateChatEntity> resultFromDB = privateChatDAO.findByUserIdAndName(savedUser.getId(), "TestName");
        assertFalse(resultFromDB.isPresent(), () -> "Private Chat must be deleted");
    }
    @Test
    @Transactional
    @Rollback
    void validEditPrivateChat() {
        //Assert
        PrivateChatEntity privateChat = PrivateChatEntity.builder()
                .userId(savedUser.getId())
                .name("TestName")
                .build();
        privateChat.setType(new ChatTypeEntity(chatTypeDAO.findIdByName("PRIVATE_CHAT"), "PRIVATE_CHAT"));
        privateChatDAO.save(privateChat);
        entityManager.detach(privateChat);
        EditPrivateChatRequest requestForEdit = new EditPrivateChatRequest(
                savedUser.getId(),
                privateChat.getId(),
                new PrivateChatInfoDTO("NewTestName")
        );


        //Action
        SUT.editPrivateChat(requestForEdit);

        //Assert
        Optional<PrivateChatEntity> resultFromDB = privateChatDAO.findByUserIdAndName(savedUser.getId(), "NewTestName");
        assertTrue(resultFromDB.isPresent(), () -> "Private Chat must be found");
        assertEquals("NewTestName", resultFromDB.get().getName());
        assertEquals("PRIVATE_CHAT", resultFromDB.get().getType().getName());
    }
}
