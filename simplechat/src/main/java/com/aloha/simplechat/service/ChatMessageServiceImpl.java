package com.aloha.simplechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.simplechat.domain.ChatMessage;
import com.aloha.simplechat.mapper.ChatMessageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatMessageServiceImpl implements ChatMessageService  {

    @Autowired private ChatMessageMapper chatMessageMapper;

    @Override
    public boolean insert(ChatMessage chatMessage) {
        log.info("insert: " + chatMessage);
        return chatMessageMapper.insert(chatMessage) > 0;
    }

    @Override
    public ChatMessage select(String id) {
        log.info("select: " + id);
        return chatMessageMapper.select(id);
    }

    @Override
    public boolean update(ChatMessage chatMessage) {
        log.info("update: " + chatMessage);
        return chatMessageMapper.update(chatMessage) > 0;
    }

    @Override
    public boolean delete(String id) {
        log.info("delete: " + id);
        return chatMessageMapper.delete(id) > 0;
    }

    @Override
    public PageInfo<ChatMessage> listByRoomNo(Long chatRoomNo, int page, int size) {
        log.info("listByRoomNo: " + chatRoomNo);
        PageHelper.startPage(page, size);
        List<ChatMessage> list = chatMessageMapper.listByRoomNo(chatRoomNo);
        PageInfo<ChatMessage> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    
}
