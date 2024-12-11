package com.aloha.simplechat.service;

import com.aloha.simplechat.domain.ChatRoomUser;
import com.github.pagehelper.PageInfo;

public interface ChatRoomUserService  {

    PageInfo<ChatRoomUser> list(int page, int size);
    
    public boolean insert(ChatRoomUser chatRoomUser);

    public ChatRoomUser select(Long no);
    public ChatRoomUser selectById(String id);
    public ChatRoomUser selectBySession(Long chatRoomNo, String sessionId);
    

    public boolean update(ChatRoomUser chatRoomUser);
    public boolean updateById(ChatRoomUser chatRoomUser);

    public boolean delete(Long no);
    public boolean deleteById(String id);

    public boolean join(ChatRoomUser chatRoomUser);
    public boolean exit(ChatRoomUser chatRoomUser);
    
}
