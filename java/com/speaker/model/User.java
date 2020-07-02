package com.speaker.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotEmpty(message = "Please enter your email.")
    private String email;

    @NotEmpty(message = "Please enter your username.")
    @Size(min = 4, max = 32, message = "Please use between 4 and 32 characters.")
    private String username;

    @NotEmpty(message = "Please enter your password.")
    @Size(min = 6, max = 100, message = "Please use between 6 and 32 characters.")
    private String password;

    private String profile;

    private String backgroundPic;

    private String description;

    private boolean enabled;


    private User(Integer id, String email, String username, String password, String profile, String backgroundPic, String description, boolean enabled) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.backgroundPic = backgroundPic;
        this.description = description;
        this.enabled = enabled;
    }


    public static class UserBuilder
    {
        private Integer id;
        private String email;
        private String username;
        private String password;
        private String profile;
        private String backgroundPic;
        private String description;
        private boolean enabled;

        public UserBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public UserBuilder setBackgroundPic(String backgroundPic) {
            this.backgroundPic = backgroundPic;
            return this;
        }

        public UserBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public UserBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }



        public User build()
        {
            return new User(id, email, username, password, profile, backgroundPic, description, enabled);
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}