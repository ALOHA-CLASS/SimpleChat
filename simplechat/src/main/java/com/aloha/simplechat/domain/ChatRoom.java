package com.aloha.simplechat.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoom {

    private Long no;
    private String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public ChatRoom() {
        this.id = UUID.randomUUID().toString();
    }
    
}
