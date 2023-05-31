package dataaccess.usecasestests.chatsusecases;

import dataaccess.jpa.JPAIntegrationEnvironment;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.jpa.jpadao.JPAChatTypeDAO;
import social.network.jpa.jpadao.JPAUserDAO;
import social.network.jpa.jpadao.JPAUserRoleDAO;
import social.network.jpa.entities.UserEntity;
import social.network.usecases.chatsusecases.ManagePrivateChatsUseCase;

import java.time.OffsetDateTime;
import java.util.List;

public class ManagePrivateChatTest extends JPAIntegrationEnvironment {
    @Autowired
    private ManagePrivateChatsUseCase SUT;
    @Autowired
    private JPAUserDAO JPAUserDAO;
    @Autowired
    private JPAUserRoleDAO JPAUserRoleDAO;
    @Autowired
    private JPAChatTypeDAO JPAChatTypeDAO;
    @Autowired
    private EntityManager entityManager;
    private UserEntity savedUser;

    @BeforeEach
    public void saveUser(){
//        savedUser = UserEntity.builder()
//                .userName("user")
//                .password("pass")
//                .roles(List.of(
//                        JPAUserRoleDAO.findByName("SIMPLE_USER")
//                ))
//                .isBlocked(false)
//                .firstName("Виктор")
//                .secondName("Кочнев")
//                .lastGetUpdatesTime(OffsetDateTime.now())
//                .build();
//        JPAUserDAO.save(savedUser);
    }

    @Test
    @Transactional
    @Rollback
    void validCreatePrivateChat() {
        //Assert

//        CreatePrivateChatRequest request = CreatePrivateChatRequest.builder()
//                .idUserSenderRequest(savedUser.getId())
//                .privateChatInfoDTO(new PrivateChatInfoDTO("TestName"))
//                .build();
//
//        //Action
//        SUT.createPrivateChat(request);
//
//        //Assert
//        Optional<PrivateChatEntity> resultFromDB = JPAPrivateChatDAO.findByUserIdAndName(savedUser.getId(), "TestName");
//        assertTrue(resultFromDB.isPresent(), () -> "Private Chat not founded");
//        assertEquals("TestName", resultFromDB.get().getName());
//        assertEquals(savedUser.getId(), resultFromDB.get().getUserId());
//        assertEquals("PRIVATE_CHAT", resultFromDB.get().getType().getName());
    }

    @Test
    @Transactional
    @Rollback
    void validRemovePrivateChat() {
        //Assert
//        PrivateChatEntity privateChat = PrivateChatEntity.builder()
//                .userId(savedUser.getId())
//                .name("TestName")
//                .build();
//        privateChat.setType(new ChatTypeEntity(JPAChatTypeDAO.findIdByName("PRIVATE_CHAT"), "PRIVATE_CHAT"));
//        JPAPrivateChatDAO.save(privateChat);
//        DeletePrivateChatRequest requestForDelete = new DeletePrivateChatRequest(
//                savedUser.getId(),
//                privateChat.getUserId()
//        );
//
//        //Action
//        SUT.deletePrivateChat(requestForDelete);
//
//        //Assert
//        Optional<PrivateChatEntity> resultFromDB = JPAPrivateChatDAO.findByUserIdAndName(savedUser.getId(), "TestName");
//        assertFalse(resultFromDB.isPresent(), () -> "Private Chat must be deleted");
    }
    @Test
    @Transactional
    @Rollback
    void validEditPrivateChat() {
        //Assert
//        PrivateChatEntity privateChat = PrivateChatEntity.builder()
//                .userId(savedUser.getId())
//                .name("TestName")
//                .build();
//        privateChat.setType(new ChatTypeEntity(JPAChatTypeDAO.findIdByName("PRIVATE_CHAT"), "PRIVATE_CHAT"));
//        JPAPrivateChatDAO.save(privateChat);
//        entityManager.detach(privateChat);
//        EditPrivateChatRequest requestForEdit = new EditPrivateChatRequest(
//                savedUser.getId(),
//                privateChat.getId(),
//                new PrivateChatInfoDTO("NewTestName")
//        );
//
//
//        //Action
//        SUT.editPrivateChat(requestForEdit);
//
//        //Assert
//        Optional<PrivateChatEntity> resultFromDB = JPAPrivateChatDAO.findByUserIdAndName(savedUser.getId(), "NewTestName");
//        assertTrue(resultFromDB.isPresent(), () -> "Private Chat must be found");
//        assertEquals("NewTestName", resultFromDB.get().getName());
//        assertEquals("PRIVATE_CHAT", resultFromDB.get().getType().getName());
    }
}
