package com.speaker.repository;

import com.speaker.model.User;
import com.speaker.service.AdminService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DBAdminRepository implements AdminService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsersAdminList()
    {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteUser(Integer id)
    {
        entityManager.createQuery("DELETE FROM User WHERE Id="+id).executeUpdate();
    }

    @Override
    @Transactional
    public void setEnabled(Integer id, boolean enabled)
    {
        entityManager.createQuery("UPDATE User SET Enabled="+enabled+" WHERE Id="+id).executeUpdate();
    }
}
