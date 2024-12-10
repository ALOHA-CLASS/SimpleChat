package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.ChatRoom;
import com.github.pagehelper.PageInfo;

public interface ChatRoomService  {
    
    public PageInfo<ChatRoom> list(int page, int size);
    public boolean insert(ChatRoom chatRoom);
    public ChatRoom select(Long no);
    public ChatRoom selectById(String id);
    public boolean update(ChatRoom chatRoom);
    public boolean delete(String id);
    
}
