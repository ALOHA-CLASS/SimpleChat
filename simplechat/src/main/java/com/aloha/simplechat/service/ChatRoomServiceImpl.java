package com.aloha.simplechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.simplechat.domain.ChatRoom;
import com.aloha.simplechat.mapper.ChatRoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomServiceImpl implements ChatRoomService  {

    @Autowired private ChatRoomMapper chatRoomMapper;

    @Override
    public boolean insert(ChatRoom chatRoom) {
        log.info("insert: " + chatRoom);
        

        return chatRoomMapper.insert(chatRoom) > 0;
    }

    @Override
    public ChatRoom select(Long no) {
        return chatRoomMapper.select(no);
    }

    @Override
    public ChatRoom selectById(String id) {
        return chatRoomMapper.selectById(id);
    }

    @Override
    public boolean update(ChatRoom chatRoom) {
        return chatRoomMapper.update(chatRoom) > 0;
    }

    @Override
    public PageInfo<ChatRoom> list(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(chatRoomMapper.list());
    }

    @Override
    public boolean delete(Long no) {
        return chatRoomMapper.delete(no) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return chatRoomMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateById(ChatRoom chatRoom) {
        return chatRoomMapper.updateById(chatRoom) > 0;
    }

    
}
