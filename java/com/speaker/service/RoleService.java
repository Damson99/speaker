package com.speaker.service;

import com.speaker.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService
{
    void persistRole(Role role);

    void setUserRole(String email, String role);

    List<Role> getUsersRoleList();

    Role getUserRole(String email);
}
