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

    @PostMapping(value = "/chat/room") //채팅방 생성
    public ResponseEntity createRoom(@RequestBody ChatDTO chatDTO){
        return ResponseEntity.ok().body(chatService.newRoom(chatDTO));
    }

    @PostMapping(value = "/chat") //채팅 input 저장
    public ResponseEntity setMsg(@RequestBody ChatDTO chatDTO){
        return ResponseEntity.ok().body(chatService.setMsg(chatDTO));
    }

    @GetMapping(value = "/chat/{room}") //채팅이력 불러오기
    public ResponseEntity getMsg(@PathVariable Integer room){
        return ResponseEntity.ok().body(chatService.getMsg(room));
    }

    @GetMapping(value = "/chat/{name}/{role}") //채팅방 리스트 불러오기
    public ResponseEntity getChatList(@PathVariable String name, @PathVariable String role){
        return ResponseEntity.ok().body(chatService.getList(name, role));
    }
}
