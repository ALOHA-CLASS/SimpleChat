package com.aloha.simplechat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.simplechat.domain.ChatMessage;


@Mapper
public interface ChatMessageMapper {

    public int insert(ChatMessage chatMessage);

    public ChatMessage select(@Param("id") String id);

    public int update(ChatMessage chatMessage);

    public int delete(@Param("id") String id);

}
