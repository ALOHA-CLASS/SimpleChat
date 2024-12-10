package com.aloha.simplechat.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatMessage {

    private Long no;
    private String id;
    private String roomId;
    private Long chatRoomNo;
    private String sessionId;
    private String content;
    private String sender;
    private Date createdAt;
    private Date updatedAt;

    private MessageType type;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ChatMessage() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

}