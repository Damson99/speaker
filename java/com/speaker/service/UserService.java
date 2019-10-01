package com.speaker.service;

import com.speaker.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService
{
    void createUser(User user);

    void updateUserProfile(User user);

    void backgroundUpload(Integer id, String background);

    void profileUpload(Integer id, String profile);

    void postUpload(UserPost userPost);

    void deletePost(Integer id);

    void postLike(PostLikes postLikes);

    void deleteLike(Integer userId, Integer postId);

    void addCom(PostComments postComments);

    void deleteCom(Integer commentId, Integer postId);

    void addFriend(Friends friend);

    void removeFriend(Integer userId, Integer userProfileId);

    void allowFriendship(Integer userId, Integer userInvitedYouId, boolean confirm);

    void declineFriendship(Integer userId, Integer userInvitedYouId);

    User findUserById(Integer id);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    List<UserPost> findUserPosts(Integer id);

    List<PostLikes> findUserPostLikes();

    List<PostLikes> findUserLikes(Integer id);

    PostLikes isLiked(Integer postId, Integer userId);

    List<PostComments> findUserPostComments();

    List<FriendsDTO> findUserFriends(Integer userId);
}