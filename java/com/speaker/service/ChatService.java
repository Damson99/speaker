package com.speaker.service;

import com.speaker.model.Message;
import com.speaker.model.OutputMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService
{
    void insertMessage(OutputMessage outputMessage);

    void increaseTotalMessages(String identifier);

    List<OutputMessage> getMessages();

    List<Message> getTotalMessages();
}
