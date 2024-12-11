package com.aloha.simplechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.simplechat.domain.ChatRoom;
import com.aloha.simplechat.domain.ChatRoomUser;
import com.aloha.simplechat.mapper.ChatRoomUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomUserServiceImpl implements ChatRoomUserService  {

    @Autowired private ChatRoomUserMapper chatRoomUserMapper;
    @Autowired private ChatRoomService chatRoomService;

    @Override
    public PageInfo<ChatRoomUser> list(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(chatRoomUserMapper.list());
    }

    @Override
    public boolean insert(ChatRoomUser chatRoomUser) {
        return chatRoomUserMapper.insert(chatRoomUser) > 0;
    }

    @Override
    public ChatRoomUser select(Long no) {
        return chatRoomUserMapper.select(no);
    }

    @Override
    public ChatRoomUser selectById(String id) {
        return chatRoomUserMapper.selectById(id);
    }

    @Override
    public boolean update(ChatRoomUser chatRoomUser) {
        return chatRoomUserMapper.update(chatRoomUser) > 0;
    }

    @Override
    public boolean updateById(ChatRoomUser chatRoomUser) {
        return chatRoomUserMapper.updateById(chatRoomUser) > 0;
    }

    @Override
    public boolean delete(Long no) {
        return chatRoomUserMapper.delete(no) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return chatRoomUserMapper.deleteById(id) > 0;
    }

    @Override
    public ChatRoomUser selectBySession(Long chatRoomNo, String sessionId) {
        return chatRoomUserMapper.selectBySession(chatRoomNo, sessionId);
    }

    @Override
    public boolean join(ChatRoomUser chatRoomUser) {
        Long chatRoomNo = chatRoomUser.getChatRoomNo();
        String sessionId = chatRoomUser.getSessionId();
        ChatRoomUser joinedUser = selectBySession(chatRoomNo, sessionId);
        if (null != joinedUser) {
            return false;
        }

        if(!insert(chatRoomUser)) {
            return false;
        }

        ChatRoom chatRoom = chatRoomService.select(chatRoomNo);
        chatRoom.setUserCount(chatRoom.getUserCount() + 1);
        chatRoomService.update(chatRoom);

        return true;
    }

    @Override
    public boolean exit(ChatRoomUser chatRoomUser) {
        Long chatRoomNo = chatRoomUser.getChatRoomNo();
        String sessionId = chatRoomUser.getSessionId();
        ChatRoomUser joinedUser = selectBySession(chatRoomNo, sessionId);
        if (null == joinedUser) {
            return false;
        }
        if(!delete(joinedUser.getNo())) {
            return false;
        }

        ChatRoom chatRoom = chatRoomService.select(chatRoomNo);
        chatRoom.setUserCount(chatRoom.getUserCount() - 1);
        chatRoomService.update(chatRoom);

        return true;
    }

    


   
    
}
