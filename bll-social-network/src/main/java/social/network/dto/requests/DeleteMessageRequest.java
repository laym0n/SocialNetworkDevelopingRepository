package social.network.dto.requests;

import lombok.Data;

@Data
public class DeleteMessageRequest {
    private int idUserOwnerRequest;
    private int idChat;
    private int orderIdMessage;
}
