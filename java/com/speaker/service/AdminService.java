package com.speaker.service;

import com.speaker.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService
{
    List<User> getUsersAdminList();

    void deleteUser(Integer id);

    void setEnabled(Integer id, boolean enabled);

}
