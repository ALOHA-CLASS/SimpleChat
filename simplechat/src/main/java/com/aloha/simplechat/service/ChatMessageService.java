package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.ChatMessage;
import com.github.pagehelper.PageInfo;

public interface ChatMessageService  {
    
    public boolean insert(ChatMessage chatMessage);
    public ChatMessage select(String id);
    public boolean update(ChatMessage chatMessage);
    public boolean delete(String id);
    public PageInfo<ChatMessage> listByRoomNo(Long chatRoomNo, int page, int size);
    
}
