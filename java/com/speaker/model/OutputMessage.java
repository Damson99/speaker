package com.speaker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class OutputMessage
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotEmpty
    private String identifier_message;

    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    @NotEmpty
    private Integer sender;

    private String time;

    public OutputMessage(String identifier_message, @NotNull @NotEmpty String message, Integer sender, String time) {
        this.identifier_message = identifier_message;
        this.message = message;
        this.sender = sender;
        this.time = time;
    }

    public OutputMessage()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier_message() {
        return identifier_message;
    }

    public void setIdentifier_message(String identifier_message) {
        this.identifier_message = identifier_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
