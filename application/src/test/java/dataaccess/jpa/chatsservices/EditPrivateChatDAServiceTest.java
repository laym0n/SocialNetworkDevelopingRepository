package dataaccess.jpa.chatsservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatProfile;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.ChatEntity;
import social.network.jpa.entities.EventEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.implbllservices.chatsservices.EditPrivateChatDAServiceImpl;
import social.network.jpa.implbllservices.chatsservices.ManagePrivateChatsDAServiceImpl;
import social.network.jpa.jpadao.JPAChatDAO;
import social.network.jpa.jpadao.JPAChatTypeDAO;
import social.network.jpa.jpadao.JPAEventDAO;
import social.network.jpa.jpadao.JPAEventTypeDAO;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EditPrivateChatDAServiceTest extends JPAIntegrationEnvironment {
    @Autowired
    private EditPrivateChatDAServiceImpl SUT;
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
    public void testUpdateAvatarAndSaveEvent() {
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
                                        .description(Optional.ofNullable("description"))
                                        .build()
                        )
                        .build()
        );
        managePrivateChatsDAService
                .createPrivateChatForUserAndSaveEvent(firstUser.getId(), privateChat, event);
        ChatInfoChangedEvent eventForArgument = new ChatInfoChangedEvent();

        //Action
        SUT.updatePrivateChatAvatarOfUserAndSaveEvent(
                privateChat.getId(),
                Optional.ofNullable(new byte[] {1}),
                eventForArgument
        );

        //Assert
        Optional<ChatEntity> chatEntityFromDB = jpaChatDAO.findById(privateChat.getId());
        assertTrue(chatEntityFromDB.isPresent(), () -> "Private chat must exist");
        assertTrue(Arrays.equals(new byte[] {1}, chatEntityFromDB.get().getAvatar()));
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdateDescriptionAndNameAndSaveEvent() {
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
        ChatInfoChangedEvent eventForArgument = new ChatInfoChangedEvent();

        //Action
        SUT.updatePrivateChatDescriptionOfUserAndSaveEvent(
                privateChat.getId(),
                new PrivateChatDescription("newName", Optional.ofNullable("newDescription")),
                eventForArgument
        );

        //Assert
        Optional<ChatEntity> chatEntityFromDB = jpaChatDAO.findById(privateChat.getId());
        assertTrue(chatEntityFromDB.isPresent(), () -> "Private chat must exist");
        assertEquals("newName", chatEntityFromDB.get().getName());
        assertEquals("newDescription", chatEntityFromDB.get().getDescription());
    }
}
