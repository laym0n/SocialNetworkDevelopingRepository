package dataaccess.jpa.chatsservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatProfile;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.implbllservices.chatsservices.CheckerOwnerPrivateChatDAServiceImpl;
import social.network.jpa.implbllservices.chatsservices.ManagePrivateChatsDAServiceImpl;
import social.network.jpa.jpadao.JPAChatDAO;
import social.network.jpa.jpadao.JPAChatTypeDAO;
import social.network.jpa.jpadao.JPAEventDAO;
import social.network.jpa.jpadao.JPAEventTypeDAO;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckerOwnerPrivateChatTest extends JPAIntegrationEnvironment {
    @Autowired
    private CheckerOwnerPrivateChatDAServiceImpl SUT;
    @Autowired
    private ManagePrivateChatsDAServiceImpl managePrivateChatsDAService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private JPAChatDAO jpaChatDAO;
    @Autowired
    private JPAEventDAO jpaEventDAO;
    @Autowired
    private JPAChatTypeDAO chatTypeDAO;
    @Autowired
    private JPAEventTypeDAO jpaEventTypeDAO;
    private UserEntity firstUser;

    @BeforeEach
    public void saveUser() {
        firstUser = UserEntity.builder()
                .userName("first")
                .password("password")
                .firstName("firstName")
                .secondName("secondName")
                .lastGetUpdatesTime(OffsetDateTime.now())
                .isBlocked(false)
                .build();
        firstUser = userDAO.create(firstUser);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindOwnerOfChat() {
        //Assign
        ChatCreatedEvent event = new ChatCreatedEvent();
        PrivateChat privateChat = new PrivateChat(
                null,
                PrivateChatProfile
                        .builder()
                        .avatar(Optional.of(new byte[] {1, 2, 3}))
                        .description(
                                PrivateChatDescription
                                        .builder()
                                        .name("test")
                                        .description(Optional.of("description"))
                                        .build()
                        )
                        .build()
        );
        managePrivateChatsDAService
                .createPrivateChatForUserAndSaveEvent(firstUser.getId(), privateChat, event);

        //Action
        int resultFromSUT = SUT.getOwnerPrivateChat(privateChat.getId());

        //Assert
        assertEquals(firstUser.getId(), resultFromSUT, () -> "Owner of chat is wrong");
    }
}
