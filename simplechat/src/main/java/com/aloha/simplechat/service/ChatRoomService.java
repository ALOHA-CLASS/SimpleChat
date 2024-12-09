package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.ChatRoom;

public interface ChatRoomService  {
    
    public boolean insert(ChatRoom chatRoom);
    public ChatRoom select(String id);
    public boolean update(ChatRoom chatRoom);
    public boolean delete(String id);
    
}
