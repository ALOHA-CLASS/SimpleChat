package com.aloha.simplechat.controller;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.simplechat.domain.ChatMessage;
import com.aloha.simplechat.domain.ChatRoom;
import com.aloha.simplechat.domain.ChatRoomUser;
import com.aloha.simplechat.domain.Pagination;
import com.aloha.simplechat.service.ChatMessageService;
import com.aloha.simplechat.service.ChatRoomService;
import com.aloha.simplechat.service.ChatRoomUserService;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ChatController {

    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomUserService chatRoomUserService;

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
        log.info("roomId : {}", id);
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

    /**
     * 채팅방 만들기
     * @param chatRoom
     * @return
     */
    @PostMapping("/ChatRoom")
    public String createChatRoom(
        ChatRoom chatRoom,
        HttpSession session
    ) {
        log.info("chatRoom: {}", chatRoom);
        chatRoom.setSessionId(session.getId());
        boolean result = chatRoomService.insert(chatRoom);
        log.info("result: {}", result);
        return "redirect:/";

    }

    @DeleteMapping("/ChatRoom/{id}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable("id") String id) {
        log.info("Deleting chat room with id: {}", id);
        boolean result = chatRoomService.deleteById(id);
        if (result) {
            log.info("Chat room deleted successfully.");
            return ResponseEntity.ok().build();
        } else {
            log.error("Failed to delete chat room.");
            return ResponseEntity.badRequest().build();
        }
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
    public ChatMessage addUser(
        @PathVariable String roomId,
        ChatMessage chatMessage,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        log.info("roomId: {}", chatMessage.getRoomId());
        String sessionId = headerAccessor.getSessionId();
        chatMessage.setSessionId(sessionId);
        chatMessage.setType(ChatMessage.MessageType.JOIN);

        ChatRoom chatRoom = chatRoomService.selectById(chatMessage.getRoomId());
        boolean result = chatRoomUserService.join(ChatRoomUser.builder()
                                                        .id(UUID.randomUUID().toString())
                                                        .chatRoomNo(chatRoom.getNo())
                                                        .sessionId(sessionId).build());
        log.info("User joined: roomId: {}, sessionId: {}", roomId, sessionId);
        log.info("result: {}", result);
        return chatMessage;
    }

    // 사용자 퇴장 (/app/chat.removeUser/{roomId})
    @MessageMapping("/chat.removeUser/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage removeUser(
        @PathVariable String roomId,
        ChatMessage chatMessage,
        SimpMessageHeaderAccessor headerAccessor
    ) {
        log.info("roomId: {}", chatMessage.getRoomId());
        String sessionId = headerAccessor.getSessionId();
        chatMessage.setSessionId(sessionId);
        chatMessage.setType(ChatMessage.MessageType.LEAVE);

        ChatRoom chatRoom = chatRoomService.selectById(chatMessage.getRoomId());
        boolean result = chatRoomUserService.exit(ChatRoomUser.builder()
                                                            .id(UUID.randomUUID().toString())
                                                            .chatRoomNo(chatRoom.getNo())
                                                            .sessionId(sessionId).build());

        log.info("User left: roomId: {}, sessionId: {}", roomId, sessionId);
        log.info("result: {}", result);
        return chatMessage;
    }


    @GetMapping("/ChatRoom/{id}/userCount")
    @ResponseBody
    public ResponseEntity<?> getUserCount(@PathVariable("id") String id) {
        ChatRoom chatRoom = chatRoomService.selectById(id);
        if (chatRoom != null) {
            log.info("User count: {}", chatRoom.getUserCount());
            return ResponseEntity.ok().body(Collections.singletonMap("userCount", chatRoom.getUserCount()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
