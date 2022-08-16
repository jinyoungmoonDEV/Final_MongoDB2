package com.example.MongoDB2.controller;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Log4j2
public class ChatController {

    private final ChatServiceImpl chatService;

    @PostMapping("/new")
    public ResponseEntity createRoom(@RequestBody ChatDTO chatDTO){
        return ResponseEntity.ok().body(chatService.createRoom(chatDTO));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity setMsg(@RequestBody ChatDTO chatDTO){
        log.info(chatDTO);
        return ResponseEntity.ok().body(chatService.setMsg(chatDTO));
    }

    @GetMapping(value = "/sender/room/{room}")
    public ResponseEntity getMsg(@PathVariable Integer room){
        log.info(chatService.getMsg(room));
        return ResponseEntity.ok().body(chatService.getMsg(room));
    }

    @GetMapping(value = "/list/{name}/{role}")//gosu, user, msg, room, createdAt
    public ResponseEntity getChatList(@PathVariable String name, @PathVariable String role){
        return ResponseEntity.ok().body(chatService.getList(name, role));
    }
}
