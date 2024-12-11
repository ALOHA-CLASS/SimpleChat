package com.aloha.simplechat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.simplechat.domain.ChatRoom;


@Mapper
public interface ChatRoomMapper {

    public int insert(ChatRoom chatRoom);

    public ChatRoom select(@Param("no") Long no);
    public ChatRoom selectById(@Param("id") String id);
    

    public int update(ChatRoom chatRoom);
    public int updateById(ChatRoom chatRoom);

    public int delete(@Param("no") Long no);
    public int deleteById(@Param("id") String id);

    public List<ChatRoom> list();

}
