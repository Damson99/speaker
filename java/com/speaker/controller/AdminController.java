package com.speaker.controller;

import com.speaker.config.WebSecurityConfig;
import com.speaker.model.Role;
import com.speaker.model.User;
import com.speaker.service.AdminService;
import com.speaker.service.RoleService;
import com.speaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class AdminController
{
    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    WebSecurityConfig webSecurityConfig;

    @GetMapping("/usersAdminList")
    public String usersAdminList(Model model)
    {
        String userEmail = webSecurityConfig.getLoggedUserEmail();
        User user = userService.findUserByEmail(userEmail);

        if(user.isEnabled()==false)
        {
            return "redirect:/logout";
        }

        List<User> usersAdminList = adminService.getUsersAdminList();
        List<Role> usersRole = roleService.getUsersRoleList();

        model.addAttribute("usersAdminList", usersAdminList);
        model.addAttribute("usersRole", usersRole);

        return "usersAdminList";
    }

    @RequestMapping(value = "userProfile/user/enable/{id}/{enabled}")
    public String setEnabledUsers(@PathVariable("id") Integer id, @PathVariable("enabled") boolean enabled)
    {
        if(enabled)
        {
            enabled = false;
        }
        else
        {
            enabled = true;
        }

        adminService.setEnabled(id, enabled);
        return "redirect:/usersAdminList";
    }

    @RequestMapping(value = "/chat/user/enable/{id}/{enabled}")
    public String setEnabledChat(@PathVariable("id") Integer id, @PathVariable("enabled") boolean enabled, Model model)
    {
        if(enabled)
        {
            enabled = false;
        }
        else
        {
            enabled = true;
        }

        adminService.setEnabled(id, enabled);
        return "redirect:/chat";
    }

    @RequestMapping(value = "/userProfile/user/delete/{id}")
    public String deleteUserList(@PathVariable("id") Integer id)
    {
        adminService.deleteUser(id);
        return "redirect:/usersAdminList";
    }

    @RequestMapping(value = "/chat/user/delete/{id}")
    public String deleteUserChat(@PathVariable("id") Integer id)
    {
        adminService.deleteUser(id);
        return "redirect:/chat";
    }

    @RequestMapping(value = "/userProfile/user/setAdmin/{email}")
    public String setAdmin(@PathVariable("email") String email)
    {
        String role = "ADMIN";
        roleService.setUserRole(email, role);

        return "redirect:/usersAdminList";
    }

    @RequestMapping(value = "/userProfile/user/setUser/{email}")
    public String setUser(@PathVariable("email") String email)
    {
        String role = "USER";
        roleService.setUserRole(email, role);

        return "redirect:/usersAdminList";
    }

}
