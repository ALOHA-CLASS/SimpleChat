package com.aloha.simplechat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.simplechat.domain.ChatRoom;


@Mapper
public interface ChatRoomMapper {

    public int insert(ChatRoom chatRoom);

    public ChatRoom select(@Param("id") String id);

    public int update(ChatRoom chatRoom);

    public int delete(@Param("id") String id);

}
