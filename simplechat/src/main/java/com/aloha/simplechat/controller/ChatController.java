package com.aloha.simplechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

    /**
     * 메인
     * @return
     */
    @GetMapping({"", "/"})
    public String home() {
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
    public String chat(@PathVariable("id") String id) {
        log.info("chat id: {}", id);

        return "chat";
    }
    
}
