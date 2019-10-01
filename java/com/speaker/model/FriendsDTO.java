package com.speaker.model;


import javax.validation.constraints.NotNull;

public class FriendsDTO
{
    @NotNull
    private Integer userId;

    @NotNull
    private Integer friendId;

    @NotNull
    private boolean confirm;

    @NotNull
    private String username;

    @NotNull
    private String profile;


    public FriendsDTO(@NotNull Integer userId, @NotNull Integer friendId, @NotNull boolean confirm, @NotNull String username, @NotNull String profile) {
        this.userId = userId;
        this.friendId = friendId;
        this.confirm = confirm;
        this.username = username;
        this.profile = profile;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
