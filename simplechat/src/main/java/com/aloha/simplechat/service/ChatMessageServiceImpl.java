package com.aloha.simplechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.simplechat.domain.ChatMessage;
import com.aloha.simplechat.mapper.ChatMessageMapper;

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

    
}
