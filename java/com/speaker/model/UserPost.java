package com.speaker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class UserPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String datePosted;

    @NotNull
    private Integer userId;

    private String description;

    @NotNull
    private String pic;

    private Integer countLikes;

    private Integer countComments;

    @Transient
    private String username;

    @Transient
    private String userProfile;


    public UserPost(Integer id, @NotNull String datePosted, @NotNull Integer userId, String description, @NotNull String pic, Integer countLikes, Integer countComments, String username, String userProfile) {
        this.id = id;
        this.datePosted = datePosted;
        this.userId = userId;
        this.description = description;
        this.pic = pic;
        this.countLikes = countLikes;
        this.countComments = countComments;
        this.username = username;
        this.userProfile = userProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(Integer countLikes) {
        this.countLikes = countLikes;
    }

    public Integer getCountComments() {
        return countComments;
    }

    public void setCountComments(Integer countComments) {
        this.countComments = countComments;
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
