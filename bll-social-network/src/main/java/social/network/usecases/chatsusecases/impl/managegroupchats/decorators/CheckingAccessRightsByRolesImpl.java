package social.network.usecases.chatsusecases.impl.managegroupchats.decorators;

import social.network.daservices.CheckingAccessRightsByRolesDAService;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;
import social.network.entities.user.UserRole;
import lombok.AllArgsConstructor;
import social.network.dto.requests.chat.*;
import social.network.usecases.chatsusecases.ManageGroupChatsUseCase;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

@AllArgsConstructor
public class CheckingAccessRightsByRolesImpl implements ManageGroupChatsUseCase {
    private CheckingAccessRightsByRolesDAService daService;
    private ManageGroupChatsUseCase manageGroupChatsUseCase;

    @Override
    public void createChat(CreateGroupChatRequest request) {
        manageGroupChatsUseCase.createChat(request);
    }

    @Override
    public void addChatMember(AddChatMemberRequest request) {
        manageGroupChatsUseCase.addChatMember(request);
    }

    @Override
    public void leaveFromChat(LeaveFromChatRequest request) {
//        checkIfFirstUserBiggerThanSecond(
//                request.getIdMemberOwnerRequest(),
//                request.getIdMemberForDelete(),
//                request.getIdChat()
//        );
        manageGroupChatsUseCase.leaveFromChat(request);

    }

    @Override
    public void editChatMember(EditChatMemberProfileRequest request) {
        checkIfFirstUserBiggerThanSecond(
                request.getIdMemberRequestOwner(),
                request.getIdChatMemberForEdit(),
                request.getIdChat()
        );
        manageGroupChatsUseCase.editChatMember(request);
    }

    @Override
    public void editChatInfo(EditChatRequest request) {
        manageGroupChatsUseCase.editChatInfo(request);
    }

    private void checkIfFirstUserBiggerThanSecond(int idFirst, int idSecond, int idChat) {
        int resultOfCompare = checkIfFirstUserRolesBiggerThanSecond(idFirst, idSecond);
        if (resultOfCompare == 0) {
            resultOfCompare = checkIfFirstChatMemberRolesBiggerThanSecond(idFirst, idSecond, idChat);
        }
        if (resultOfCompare != 1) {
            throw new RejectedExecutionException("No rights for this operation");
        }
    }

    private int checkIfFirstUserRolesBiggerThanSecond(int idFirst, int idSecond) {
        Map<Integer, UserRole> firstRoles = daService.findUserRoles(idFirst);
        Map<Integer, UserRole> secondRoles = daService.findUserRoles(idSecond);
        int maxFirstPower = firstRoles.values().stream().max(Comparator.comparingInt(UserRole::getPower)).get().getPower();
        int maxSecondPower = secondRoles.values().stream().max(Comparator.comparingInt(UserRole::getPower)).get().getPower();
        return Integer.compare(maxFirstPower, maxSecondPower);
    }

    private int checkIfFirstChatMemberRolesBiggerThanSecond(int idFirst, int idSecond, int idChat) {
        Map<Integer, ChatMemberRole> firstRoles = daService.findChatMemberRoles(idChat, idFirst);
        Map<Integer, ChatMemberRole> secondRoles = daService.findChatMemberRoles(idChat, idSecond);
        int maxFirstPower = firstRoles.values().stream().max(Comparator.comparingInt(ChatMemberRole::getPower)).get().getPower();
        int maxSecondPower = secondRoles.values().stream().max(Comparator.comparingInt(ChatMemberRole::getPower)).get().getPower();
        return Integer.compare(maxFirstPower, maxSecondPower);
    }
}
