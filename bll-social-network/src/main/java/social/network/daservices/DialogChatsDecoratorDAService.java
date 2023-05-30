package social.network.daservices;

public interface DialogChatsDecoratorDAService {
    void setIsFrozenDueToBlackListIfExists(int idSecondUser, int idFirstUser, boolean value);
}
