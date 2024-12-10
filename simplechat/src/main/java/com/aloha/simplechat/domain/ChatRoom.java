package com.aloha.simplechat.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoom {

    private Long no;
    private String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    private List<ChatMessage> messages = new ArrayList<>();

    public ChatRoom() {
        this.id = UUID.randomUUID().toString();
    }
    
}
