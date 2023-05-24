package userusecases;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import social.network.daservices.ManageFriendsRequestsDAService;
import social.network.dto.requests.SendNewFriendRequestRequest;
import social.network.entities.usersrelationships.friends.FriendRequest;
import social.network.exceptions.EntityAlreadyExistsException;
import social.network.exceptions.UserInBlackListException;
import social.network.usecases.usersusecases.impl.managefriendrequests.ManageFriendRequestUseCaseImpl;

import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ManageFriendRequestsUseCaseTest {
    @Test
    public void testValidSendRequest() {
        //Assign
        ManageFriendsRequestsDAService daServiceMock = mock(ManageFriendsRequestsDAService.class);
        when(daServiceMock.isExistFriendRelationship(any(Integer.class), any(Integer.class)))
                .thenReturn(false);
        when(daServiceMock.isBlackListRelationshipExistBetweenUsers(any(Integer.class), any(Integer.class)))
                .thenReturn(false);

        ManageFriendRequestUseCaseImpl SUT = new ManageFriendRequestUseCaseImpl(daServiceMock);
        SendNewFriendRequestRequest request = SendNewFriendRequestRequest.builder()
                .idUserSenderRequest(5)
                .idUserForWhomRequest(10)
                .build();

        //Action
        SUT.sendNewRequest(request);

        //Assert
        ArgumentCaptor<FriendRequest> argumentForDaService = ArgumentCaptor.forClass(FriendRequest.class);
        verify(daServiceMock).createFriendRequestAndCheckIfAlreadyExists(argumentForDaService.capture());
        FriendRequest savedFriendRequest = argumentForDaService.getValue();
        savedFriendRequest.setWhenSent(savedFriendRequest.getWhenSent().truncatedTo(ChronoUnit.MINUTES));
        assertEquals(FriendRequest.builder()
                        .idUserSender(5)
                        .idUserToWhom(10)
                        .whenSent(OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                        .build(),
                savedFriendRequest
        );
    }

    @Test
    public void testSendRequestForYourself_ExpectedInvalidParameterException() {
        //Assign
        ManageFriendsRequestsDAService daServiceMock = mock(ManageFriendsRequestsDAService.class);

        ManageFriendRequestUseCaseImpl SUT = new ManageFriendRequestUseCaseImpl(daServiceMock);
        SendNewFriendRequestRequest request = SendNewFriendRequestRequest.builder()
                .idUserSenderRequest(5)
                .idUserForWhomRequest(5)
                .build();

        //Action
        assertThrows(InvalidParameterException.class, () -> SUT.sendNewRequest(request));
    }

    @Test
    public void testSendFriendRequestBetweenUsersInBlackList_ExpectedUserInBlackListException() {
        //Assign
        ManageFriendsRequestsDAService daServiceMock = mock(ManageFriendsRequestsDAService.class);
        when(daServiceMock.isExistFriendRelationship(any(Integer.class), any(Integer.class)))
                .thenReturn(false);
        when(daServiceMock.isBlackListRelationshipExistBetweenUsers(any(Integer.class), any(Integer.class)))
                .thenReturn(true);

        ManageFriendRequestUseCaseImpl SUT = new ManageFriendRequestUseCaseImpl(daServiceMock);
        SendNewFriendRequestRequest request = SendNewFriendRequestRequest.builder()
                .idUserSenderRequest(5)
                .idUserForWhomRequest(10)
                .build();

        //Action
        assertThrows(UserInBlackListException.class, () -> SUT.sendNewRequest(request));
    }

    @Test
    public void testSendFriendRequestBetweenFriends_ExpectedEntityAlreadyExistsException() {
        //Assign
        ManageFriendsRequestsDAService daServiceMock = mock(ManageFriendsRequestsDAService.class);
        when(daServiceMock.isExistFriendRelationship(any(Integer.class), any(Integer.class)))
                .thenReturn(true);
        when(daServiceMock.isBlackListRelationshipExistBetweenUsers(any(Integer.class), any(Integer.class)))
                .thenReturn(false);

        ManageFriendRequestUseCaseImpl SUT = new ManageFriendRequestUseCaseImpl(daServiceMock);
        SendNewFriendRequestRequest request = SendNewFriendRequestRequest.builder()
                .idUserSenderRequest(5)
                .idUserForWhomRequest(10)
                .build();

        //Action
        assertThrows(EntityAlreadyExistsException.class, () -> SUT.sendNewRequest(request));
    }
}
