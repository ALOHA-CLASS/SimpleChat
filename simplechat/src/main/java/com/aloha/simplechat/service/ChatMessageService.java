package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.ChatMessage;

public interface ChatMessageService  {
    
    public boolean insert(ChatMessage chatMessage);
    public ChatMessage select(String id);
    public boolean update(ChatMessage chatMessage);
    public boolean delete(String id);
    
}
