package dataaccess.jpa.chatsservices;

import dataaccess.jpa.JPAIntegrationEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatProfile;
import social.network.jpa.dao.UserDAO;
import social.network.jpa.entities.ChatEntity;
import social.network.jpa.entities.EventEntity;
import social.network.jpa.entities.UserEntity;
import social.network.jpa.implbllservices.chatsservices.ManagePrivateChatsDAServiceImpl;
import social.network.jpa.jpadao.JPAChatDAO;
import social.network.jpa.jpadao.JPAChatTypeDAO;
import social.network.jpa.jpadao.JPAEventDAO;
import social.network.jpa.jpadao.JPAEventTypeDAO;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ManagePrivateChatsDATest extends JPAIntegrationEnvironment {
    @Autowired
    private ManagePrivateChatsDAServiceImpl SUT;
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

    @ParameterizedTest
    @ArgumentsSource(CreateChatArguments.class)
    @Transactional
    @Rollback
    public void validCreateChatTest(PrivateChat privateChat, ChatEntity expectedResult) {
        //Assign
        ChatCreatedEvent event = new ChatCreatedEvent();

        //Action
        SUT.createPrivateChatForUserAndSaveEvent(
                firstUser.getId(),
                privateChat,
                event
        );

        //Assert
        List<ChatEntity> chatsFromDB = jpaChatDAO.findAll();
        assertEquals(1, chatsFromDB.size(), () -> "Db must contain 1 chat");
        expectedResult.setId(chatsFromDB.get(0).getId());
        expectedResult.setType(chatTypeDAO.findByName("PRIVATE_CHAT"));
        assertEquals(expectedResult, chatsFromDB.get(0));

        List<EventEntity> eventsFromDB = jpaEventDAO.findAll();
        assertEquals(1, eventsFromDB.size(), () -> "Db must contain 1 chat event");
        assertEquals(jpaEventTypeDAO.findByName("CHAT_CREATED").get(), eventsFromDB.get(0).getType());
    }

    @Test
    @Transactional
    @Rollback
    public void testSetNotActiveAndSaveEvent() {
        //Assign
        ChatCreatedEvent event = new ChatCreatedEvent();
        PrivateChat privateChat = new PrivateChat(
                null,
                PrivateChatProfile
                        .builder()
                        .avatar(Optional.of(new byte[]{1, 2, 3}))
                        .description(
                                PrivateChatDescription
                                        .builder()
                                        .name("test")
                                        .description(Optional.of("description"))
                                        .build()
                        )
                        .build()
        );
        SUT.createPrivateChatForUserAndSaveEvent(firstUser.getId(), privateChat, event);

        ChatDeletedEvent deletedEvent = new ChatDeletedEvent();

        //Action
        SUT.SaveEventForChatAndSetNotActiveChat(privateChat.getId(), deletedEvent);

        //Assert
        Optional<ChatEntity> chatEntityFromDB = jpaChatDAO.findById(privateChat.getId());
        assertTrue(chatEntityFromDB.isPresent(), () -> "Private chat must exist");
        assertFalse(chatEntityFromDB.get().isActive());
        List<EventEntity> eventsFromDB = jpaEventDAO.findByPrimaryKey_ChatId(privateChat.getId());
        List<EventEntity> asfd = jpaEventDAO.findAll();
        assertTrue(2 <= eventsFromDB.size());
        assertEquals(jpaEventTypeDAO.findByName("CHAT_DELETED").get(), eventsFromDB.get(eventsFromDB.size() - 1).getType());
    }

    static class CreateChatArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Arguments firstArguments;
            {
                firstArguments = Arguments.of(
                        new PrivateChat(
                                null,
                                PrivateChatProfile
                                        .builder()
                                        .avatar(Optional.empty())
                                        .description(
                                                PrivateChatDescription
                                                        .builder()
                                                        .name("test")
                                                        .description(Optional.of("description"))
                                                        .build()
                                        )
                                        .build()
                        ),
                        ChatEntity
                                .builder()
                                .avatar(null)
                                .description("description")
                                .name("test")
                                .isActive(true)
                                .isBlocked(false)
                                .rules(null)
                                .build()
                );
            }
            Arguments secondArguments;
            {
                secondArguments = Arguments.of(
                        new PrivateChat(
                                null,
                                PrivateChatProfile
                                        .builder()
                                        .avatar(Optional.of(new byte[]{1, 2, 3}))
                                        .description(
                                                PrivateChatDescription
                                                        .builder()
                                                        .name("test")
                                                        .description(Optional.of("description"))
                                                        .build()
                                        )
                                        .build()
                        ),
                        ChatEntity
                                .builder()
                                .avatar(new byte[]{1, 2, 3})
                                .description("description")
                                .name("test")
                                .isActive(true)
                                .isBlocked(false)
                                .rules(null)
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
