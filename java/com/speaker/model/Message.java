package com.speaker.model;


import javax.persistence.*;

@Entity
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String identifier_message;

    private Integer total_messages;

    @Transient
    private String text;


    public Message() {
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

    public Integer getTotal_messages() {
        return total_messages;
    }

    public void setTotal_messages(Integer total_messages) {
        this.total_messages = total_messages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
