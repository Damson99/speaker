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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/main";
    }

    @PostMapping("/searchResults")
    public String searchResults(Model model, @RequestParam("searchUser") String username)
    {
        Object isLogged = "";
        User user = new User();
        try
        {
            isLogged = webSecurityConfig.isUserLogged();
            if(isLogged!=null)
            {
                String userEmail = webSecurityConfig.getLoggedUserEmail();
                user = userService.findUserByEmail(userEmail);
                if(!user.isEnabled())
                {
                    return "redirect:/logout";
                }

                Integer id = user.getId();
                List<FriendsDTO> friends = userService.findUserFriends(id);

                String friend = "Add friend";
                for(FriendsDTO isFriend : friends)
                {
                    if(user.getId().equals(isFriend.getUserId()))
                    {
                        if(isFriend.isConfirm())
                        {
                            friend = "Remove friend";
                        }
                        else
                        {
                            friend = "Request sent";
                        }
                        break;
                    }
                    else
                    {
                        friend = "Add friend";
                    }
                }

                model.addAttribute("friend", friend);
                model.addAttribute("friends", friends);
            }
            else
            {
                user = null;
            }
        }
        catch (ClassCastException ex)
        {

        }

        List<User> users = mainService.findUserByUsername(username);

        if(users.equals("") || users==null)
        {
            String emptyRecord = "No users with this username";
            model.addAttribute("message", emptyRecord);
        }
        else
        {
            model.addAttribute("users", users);
        }

        model.addAttribute("user", user);
        return "/searchResults";
    }
}
