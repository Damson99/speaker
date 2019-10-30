package com.speaker.service;

import com.speaker.model.User;
import com.speaker.model.UserPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainService
{
    List<UserPost> findPosts();

    List<User> findUserByUsername(String username);
}
