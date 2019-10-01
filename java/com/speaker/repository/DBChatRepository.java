package com.speaker.repository;

import com.speaker.model.Message;
import com.speaker.model.OutputMessage;
import com.speaker.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DBChatRepository implements ChatService
{
    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public void insertMessage(OutputMessage outputMessage)
    {
        entityManager.persist(outputMessage);
    }

    @Override
    @Transactional
    public void increaseTotalMessages(String identifier)
    {
        entityManager.createQuery("INSERT INTO Message(identifier_messages, total_messages) " +
                "VALUES('"+identifier+"', 1 ON DUPLICATE KEY UPDATE total_messages = total_messages + 1");
    }

    @Override
    public List<Message> getTotalMessages()
    {
        return entityManager.createQuery("FROM Message", Message.class).getResultList();
    }

    @Override
    public List<OutputMessage> getMessages()
    {
        return entityManager.createQuery("FROM OutputMessage", OutputMessage.class).getResultList();
    }
}
