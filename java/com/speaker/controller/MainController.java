package com.speaker.controller;

import com.speaker.config.WebSecurityConfig;
import com.speaker.model.*;
import com.speaker.service.MainService;
import com.speaker.service.RoleService;
import com.speaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController
{
    @Autowired
    private MainService mainService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService RoleService;

    @Autowired
    private WebSecurityConfig webSecurityConfig;


    @GetMapping("/")
    public String main(Model model)
    {
        Object isLogged = "";
        Object privileges = "";
        List<PostLikes> userLikes = new ArrayList<>();
        List<FriendsDTO> friends = new ArrayList<>();
        User user = new User();
        try
        {
            isLogged = webSecurityConfig.getLoggedUserEmail();
        }
        catch(ClassCastException ex)
        {

        }
        if(!isLogged.equals(""))
        {
            String userEmail = webSecurityConfig.getLoggedUserEmail();
            privileges = webSecurityConfig.userPrivileges();
            user = userService.findUserByEmail(userEmail);
            Integer id;
            if(!user.isEnabled())
            {
                return "redirect:/logout";
            }
            else
            {
                id = user.getId();
                userLikes = userService.findUserLikes(id);
                friends = userService.findUserFriends(id);
            }
        }

        List<UserPost> userPosts = mainService.findPosts();
        List<PostLikes> postLikes = userService.findUserPostLikes();
        List<PostComments> postComments = userService.findUserPostComments();
        List<User> allUsers = userService.getAllUsers();

        model.addAttribute("user", user);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("privileges", privileges);
        model.addAttribute("friends", friends);
        model.addAttribute("userLikes", userLikes);
        model.addAttribute("userPosts", userPosts);
        model.addAttribute("postLikes", postLikes);
        model.addAttribute("postComments", postComments);
        return "main";
    }
}
