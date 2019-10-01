package com.speaker.controller;

import com.speaker.config.WebSecurityConfig;
import com.speaker.model.*;
import com.speaker.service.ChatService;
import com.speaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController
{
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private SessionRegistry sessionRegistry;


    @GetMapping("/chat")
    public String chat(Model model, User user)
    {
        if(webSecurityConfig.isUserLogged())
        {
            List<User> users = userService.getAllUsers();

            String userEmail = webSecurityConfig.getLoggedUserEmail();
            Object privileges = webSecurityConfig.userPrivileges();
            user = userService.findUserByEmail(userEmail);
            if(user.isEnabled()==false)
            {
                return "redirect:/logout";
            }
            List<OutputMessage> userMessages = chatService.getMessages();
            List<Message> scoreMessages = chatService.getTotalMessages();
            List<FriendsDTO> friends = userService.findUserFriends(user.getId());


            model.addAttribute("allusers", users);
            model.addAttribute("privileges", privileges);
            model.addAttribute("user", user);
            model.addAttribute("usermessages", userMessages);
            model.addAttribute("friends", friends);
            model.addAttribute("scoremessages", scoreMessages);
        }
        else
        {
            model.addAttribute("message", "Please log in or register first");
        }
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message, OutputMessage outputMessage) throws Exception
    {
        String time = new SimpleDateFormat("HH:mm").format(new Date());

        String[] senderToConvert = outputMessage.getIdentifier_message().split(":");
        String senderString = senderToConvert[1];
        int sender = Integer.parseInt(senderString);

        outputMessage.setIdentifier_message(message.getIdentifier_message());
        outputMessage.setMessage(message.getText());
        outputMessage.setSender(sender);
        outputMessage.setTime(time);


        chatService.increaseTotalMessages(message.getIdentifier_message());
        chatService.insertMessage(outputMessage);
        return new OutputMessage(message.getIdentifier_message(), message.getText(), sender, time);
    }
}
