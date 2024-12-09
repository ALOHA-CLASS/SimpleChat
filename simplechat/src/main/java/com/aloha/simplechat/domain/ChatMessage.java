package com.aloha.simplechat.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatMessage {

    private Long no;
    private String id;
    private Long chatRoomNo;
    private String sessionId;
    private String message;
    private Date createdAt;
    private Date updatedAt;

    public ChatMessage() {
        this.id = UUID.randomUUID().toString();
    }

}
