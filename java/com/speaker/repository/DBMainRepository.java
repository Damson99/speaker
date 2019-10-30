package com.speaker.repository;

import com.speaker.model.User;
import com.speaker.model.UserPost;
import com.speaker.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class DBMainRepository implements MainService
{
    @Autowired
    EntityManager entityManager;


    @Override
    public List<UserPost> findPosts()
    {
        List<UserPost> userPosts;
        try
        {
            userPosts = entityManager.createQuery("SELECT NEW com.speaker.model.UserPost(up.id, up.datePosted, up.userId," +
                    " up.description, up.pic, up.countLikes, up.countComments, u.username, u.profile)" +
                    " FROM UserPost up LEFT JOIN User u ON up.userId=u.id" +
                    " ORDER BY up.id DESC", UserPost.class).getResultList();
        }
        catch(NoResultException e)
        {
            userPosts = null;
        }
        return userPosts;
    }

    @Override
    public List<User> findUserByUsername(String username)
    {
        List<User> users;
        try
        {
            users = entityManager.createQuery("FROM User WHERE username LIKE '"+username+"%'", User.class).getResultList();
        }
        catch (NoResultException e)
        {
            users = null;
        }
        return users;
    }
}
