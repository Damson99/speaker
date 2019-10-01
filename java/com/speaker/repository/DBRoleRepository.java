package com.speaker.repository;

import com.speaker.model.Role;
import com.speaker.service.RoleService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DBRoleRepository implements RoleService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persistRole(Role role)
    {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void setUserRole(String email, String role)
    {
        entityManager.createQuery("UPDATE Role SET role='"+role+"' WHERE Email='"+email+"'").executeUpdate();
    }

    @Override
    public List<Role> getUsersRoleList()
    {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getUserRole(String email)
    {
        return entityManager.createQuery("FROM Role WHERE Email='"+email+"'", Role.class).getSingleResult();
    }
}