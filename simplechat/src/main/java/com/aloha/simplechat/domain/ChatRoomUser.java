package com.aloha.simplechat.domain;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatRoomUser {

    private Long no;
    private String id;
    private Long chatRoomNo;
    private String sessionId;
    private Date createdAt;
    private Date updatedAt;

    public ChatRoomUser() {
        this.id = UUID.randomUUID().toString();
    }

    
}
