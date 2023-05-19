package social.network.daservices;

public interface CheckAccessRightByContainsInChatDAService {
    boolean isChatContainsChatMember(int idChat, int idUserInviter);
}
