package social.network.usecases.newsfeedsusecases;

import social.network.dto.requests.AddPostToUserProfileRequest;
import social.network.dto.requests.DeletePostFromUserProfileRequest;
import social.network.dto.requests.EditPostInUserProfileRequest;

public interface ManageNewsFeedsUseCase {
    void addPost(AddPostToUserProfileRequest request);

    void deletePost(DeletePostFromUserProfileRequest request);

    void editPost(EditPostInUserProfileRequest request);
}
