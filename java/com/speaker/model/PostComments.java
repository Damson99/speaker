package com.speaker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PostComments
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer postId;

    @NotNull
    private Integer userId;

    @NotNull
    private String description;

    @Transient
    private String username;

    @Transient
    private String userProfile;


    private PostComments(Integer id, @NotNull Integer postId, @NotNull Integer userId, @NotNull String description, String username, String userProfile) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.description = description;
        this.username = username;
        this.userProfile = userProfile;
    }

    public static class PostCommentsBuilder
    {
        private Integer id;
        private Integer postId;
        private Integer userId;
        private String description;
        private String username;
        private String userProfile;

        public PostCommentsBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public PostCommentsBuilder setPostId(Integer postId) {
            this.postId = postId;
            return this;
        }

        public PostCommentsBuilder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public PostCommentsBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public PostCommentsBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public PostCommentsBuilder setUserProfile(String userProfile) {
            this.userProfile = userProfile;
            return this;
        }


        public PostComments build()
        {
            return new PostComments(id, postId, userId, description, username, userProfile);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
