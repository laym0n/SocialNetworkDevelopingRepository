package social.network.usecases.newsfeedsusecases;

import social.network.dto.requests.AddPostRequest;
import social.network.dto.requests.DeletePostRequest;
import social.network.dto.requests.EditPostRequest;

public interface ManageNewsFeedPostsUseCase {
    void addNewPost(AddPostRequest request);

    void deletePost(DeletePostRequest request);

    void editPost(EditPostRequest request);
}
