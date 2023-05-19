package social.network.usecases.chatsusecases.impl.managegroupchats.strategies;

import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

public class CompareChatMembersByRolesStrategy implements Comparator<Map<Integer, ChatMemberRole>> {

    @Override
    public int compare(Map<Integer, ChatMemberRole> firstRoles, Map<Integer, ChatMemberRole> secondRoles) {
        Optional<ChatMemberRole> firstMaxRole = firstRoles.values().stream()
                .max(Comparator.comparingInt(ChatMemberRole::getPower));
        Optional<ChatMemberRole> secondMaxRole = secondRoles.values().stream()
                .max(Comparator.comparingInt(ChatMemberRole::getPower));
        if (firstRoles.isEmpty() || secondRoles.isEmpty()) {
            throw new RejectedExecutionException("Roles is empty");
        }
        return Integer.compare(firstMaxRole.get().getPower(), secondMaxRole.get().getPower());
    }
}
