package social.network.dto.requests;

import lombok.Data;

@Data
public class DeleteMessageOnlyForUserRequest {
    private int idUserSenderRequest;
    private int chatId;
    private int orderIdMessage;
}
