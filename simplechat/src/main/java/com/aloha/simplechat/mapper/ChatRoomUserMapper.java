package com.aloha.simplechat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.simplechat.domain.ChatRoomUser;


@Mapper
public interface ChatRoomUserMapper {

    public List<ChatRoomUser> list();

    public int insert(ChatRoomUser chatRoomUser);

    public ChatRoomUser select(@Param("no") Long no);
    public ChatRoomUser selectById(@Param("id") String id);
    public ChatRoomUser selectBySession(@Param("chatRoomNo") Long chatRoomNo, @Param("sessionId") String sessionId);

    public int update(ChatRoomUser chatRoomUser);
    public int updateById(ChatRoomUser chatRoomUser);

    public int delete(@Param("no") Long no);
    public int deleteById(@Param("id") String id);



}
