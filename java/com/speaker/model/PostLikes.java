package com.speaker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PostLikes
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer postId;

    @NotNull
    private Integer userId;

    @Transient
    private String username;

    @Transient
    private String userProfile;


    public PostLikes(Integer id, @NotNull Integer postId, @NotNull Integer userId, String username, String userProfile) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.userProfile = userProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
}
