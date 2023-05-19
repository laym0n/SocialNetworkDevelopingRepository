package social.network.usecases.newsfeedsusecases;

import social.network.dto.requests.AddNewCommentRequest;
import social.network.dto.requests.DeleteCommentRequest;
import social.network.dto.requests.EditCommentRequest;

public interface ManagePostCommentsUseCase {
    void addNewComment(AddNewCommentRequest request);

    void deleteComment(DeleteCommentRequest request);

    void editComment(EditCommentRequest request);
}
