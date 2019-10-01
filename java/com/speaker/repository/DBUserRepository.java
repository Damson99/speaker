package com.speaker.repository;

import com.speaker.model.*;
import com.speaker.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class DBUserRepository implements UserService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createUser(User user)
    {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUserProfile(User user)
    {
        entityManager.createQuery("UPDATE User SET description='"+user.getDescription()
                +"',username='"+user.getUsername()
                +"' WHERE id="+user.getId()).executeUpdate();
    }

    @Transactional
    @Override
    public void backgroundUpload(Integer id, String background)
    {
        entityManager.createQuery("UPDATE User SET background_pic='"+background+"' WHERE Id="+id).executeUpdate();
    }

    @Transactional
    @Override
    public void profileUpload(Integer id, String profile)
    {
        entityManager.createQuery("UPDATE User SET profile='"+profile+"' WHERE Id="+id).executeUpdate();
    }

    @Override
    @Transactional
    public void postUpload(UserPost userPost)
    {
        entityManager.persist(userPost);
    }

    @Override
    @Transactional
    public void deletePost(Integer id)
    {
        entityManager.createQuery("DELETE FROM UserPost WHERE id="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM PostComments WHERE postId="+id).executeUpdate();
        entityManager.createQuery("DELETE FROM PostLikes WHERE postId="+id).executeUpdate();
    }

    @Override
    @Transactional
    public void postLike(PostLikes postLikes)
    {
        entityManager.persist(postLikes);
        entityManager.createQuery("Update UserPost set countLikes=countLikes + 1 WHERE id="+postLikes.getPostId()).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteLike(Integer userId, Integer postId)
    {
        entityManager.createQuery("DELETE FROM PostLikes WHERE userId=:userId AND postId=:postId")
                .setParameter("userId", userId).setParameter("postId", postId).executeUpdate();
        entityManager.createQuery("Update UserPost set countLikes=countLikes - 1 WHERE id="+postId).executeUpdate();
    }

    @Override
    @Transactional
    public void addCom(PostComments postComments)
    {
        entityManager.persist(postComments);
        entityManager.createQuery("Update UserPost set countComments=countComments + 1 WHERE id="+postComments.getPostId()).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteCom(Integer commentId, Integer postId)
    {
        entityManager.createQuery("DELETE FROM PostComments WHERE Id="+commentId).executeUpdate();
        entityManager.createQuery("Update UserPost set countComments=countComments - 1 WHERE id="+postId).executeUpdate();
    }

    @Override
    @Transactional
    public void addFriend(Friends friend)
    {
        entityManager.persist(friend);
    }

    @Override
    @Transactional
    public void removeFriend(Integer userId, Integer userProfileId)
    {
        entityManager.createQuery("DELETE FROM Friends WHERE userId="+userId+" AND friendId="+userProfileId).executeUpdate();
        entityManager.createQuery("DELETE FROM Friends WHERE userId="+userProfileId+" AND friendId="+userId).executeUpdate();
    }

    @Override
    @Transactional
    public void allowFriendship(Integer userId, Integer userInvitedYouId, boolean confirm)
    {
        entityManager.createQuery("Update Friends SET confirm="+confirm+" WHERE friendId="+userId+" AND userId="+userInvitedYouId).executeUpdate();
    }

    @Override
    @Transactional
    public void declineFriendship(Integer userId, Integer userInvitedYouId)
    {
        entityManager.createQuery("DELETE FROM Friends WHERE userId="+userInvitedYouId+" AND friendId="+userId).executeUpdate();
    }

    @Override
    public User findUserById(Integer id)
    {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByEmail(String email)
    {
        User user;
        try
        {
            user = entityManager.createQuery("FROM User u where u.email=:email", User.class)
                    .setParameter("email", email).getSingleResult();
        }
        catch (NoResultException e)
        {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers()
    {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public List<UserPost> findUserPosts(Integer id)
    {
        List<UserPost> userPosts;
        try
        {
            userPosts = entityManager.createQuery("SELECT NEW com.speaker.model.UserPost(up.id, up.datePosted, up.userId," +
                    " up.description, up.pic, up.countLikes, up.countComments, u.username, u.profile)" +
                    " FROM UserPost up LEFT JOIN User u ON up.userId=u.id" +
                    " WHERE up.userId="+id+" ORDER BY up.id DESC", UserPost.class).getResultList();
        }
        catch(NoResultException e)
        {
            userPosts = null;
        }
        return userPosts;
    }

    @Override
    public List<PostLikes> findUserPostLikes()
    {
        List<PostLikes> postLikes;
        try
        {
            postLikes = entityManager.createQuery("SELECT NEW com.speaker.model.PostLikes(pl.id, pl.postId, pl.userId," +
                    " u.username, u.profile)" +
                    " FROM PostLikes pl LEFT JOIN User u ON pl.userId=u.id", PostLikes.class).getResultList();
        }
        catch(NoResultException e)
        {
            postLikes = null;
        }
        return postLikes;
    }

    @Override
    public List<PostLikes> findUserLikes(Integer id)
    {
        List<PostLikes> postLikes;
        try
        {
            postLikes = entityManager.createQuery("SELECT NEW com.speaker.model.PostLikes(pl.id, pl.postId, pl.userId," +
                    " u.username, u.profile)" +
                    " FROM PostLikes pl LEFT JOIN User u ON pl.userId=u.id WHERE pl.userId="+id, PostLikes.class).getResultList();
        }
        catch(NoResultException e)
        {
            postLikes = null;
        }
        return postLikes;
    }

    @Override
    public PostLikes isLiked(Integer postId, Integer userId)
    {
        PostLikes like;
        try
        {
            like = entityManager.createQuery("SELECT NEW com.speaker.model.PostLikes(pl.id, pl.postId, pl.userId," +
                    " u.username, u.profile) FROM PostLikes pl LEFT JOIN User u ON pl.userId=u.id" +
                    " WHERE pl.postId=:postId AND pl.userId=:userId", PostLikes.class)
                    .setParameter("postId", postId).setParameter("userId", userId).getSingleResult();
        }
        catch(NoResultException e)
        {
            like = null;
        }
        return like;
    }

    @Override
    public List<PostComments> findUserPostComments()
    {
        List<PostComments> postComments;
        try
        {
            postComments = entityManager.createQuery("SELECT NEW com.speaker.model.PostComments(pc.id, pc.postId, pc.userId, " +
                    "pc.description, u.username, u.profile)" +
                    " FROM PostComments pc LEFT JOIN User u ON pc.userId=u.id", PostComments.class).getResultList();
        }
        catch(NoResultException e)
        {
            postComments = null;
        }
        return postComments;
    }

    @Override
    public List<FriendsDTO> findUserFriends(Integer userId)
    {
        List<FriendsDTO> friends;
        try
        {
            friends = entityManager.createQuery("SELECT NEW com.speaker.model.FriendsDTO(f.userId, f.friendId, f.confirm, u.username, u.profile)" +
                    " FROM Friends f LEFT JOIN User u ON f.userId=u.id WHERE f.friendId=:id", FriendsDTO.class).setParameter("id", userId).getResultList();
        }
        catch(NoResultException e)
        {
            friends = null;
        }
        return friends;
    }
}
