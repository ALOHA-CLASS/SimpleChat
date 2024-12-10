package com.aloha.simplechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.simplechat.domain.ChatMessage;
import com.aloha.simplechat.domain.ChatRoom;
import com.aloha.simplechat.domain.Pagination;
import com.aloha.simplechat.service.ChatMessageService;
import com.aloha.simplechat.service.ChatRoomService;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ChatController {

    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatMessageService chatMessageService;

    /**
     * 메인
     * @return
     */
    @GetMapping({"", "/"})
    public String home(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "6") int size,
        Model model,
        Pagination pagination
    ) {

        PageInfo<ChatRoom> pageInfo = chatRoomService.list(page, size);
        pagination.setTotal(pageInfo.getTotal());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pagination", pagination);
        return "index";
    }

    /**
     * 채팅방 만들기
     * @return
     */
    @GetMapping("/create")
    public String create() {

        return "create";
    }

    /**
     * 채팅방
     * @param id
     * @return
     */
    @GetMapping("/chat/{id}")
    public String chat(
        @PathVariable("id") String id,
        Model model,
        HttpSession session,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        log.info("chat id: {}", id);
        model.addAttribute("roomId", id);
        String sessionId = session.getId();
        model.addAttribute("sessionId", sessionId);
        ChatRoom chatRoom = chatRoomService.selectById(id);
        model.addAttribute("chatRoom", chatRoom);
        Long roomNo = null != chatRoom ? chatRoom.getNo() : null;
        PageInfo<ChatMessage> pageInfo = chatMessageService.listByRoomNo(roomNo, page, size);
        model.addAttribute("pageInfo", pageInfo);

        return "chat";
    }

    @PostMapping("/ChatRoom")
    public String createChatRoom(ChatRoom chatRoom) {
        log.info("chatRoom: {}", chatRoom);
        boolean result = chatRoomService.insert(chatRoom);
        log.info("result: {}", result);
        return "redirect:/";

    }
    
    
    // 메시지 전송 (/app/chat.sendMessage/{roomId})
    @MessageMapping("/chat.sendMessage/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(
        @PathVariable String roomId,
        ChatMessage chatMessage
    ) {
        log.info("roomId: {}", chatMessage.getRoomId());
        ChatRoom chatRoom = chatRoomService.selectById(chatMessage.getRoomId());
        chatMessage.setChatRoomNo(null != chatRoom ? chatRoom.getNo() : null);
        boolean result = chatMessageService.insert(chatMessage);
        log.info("result: {}", result);

        return chatMessage;
    }


    // 사용자 입장 (/app/chat.addUser/{roomId})
    @MessageMapping("/chat.addUser/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage addUser(@PathVariable String roomId, ChatMessage chatMessage) {
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        return chatMessage;
    }
}
