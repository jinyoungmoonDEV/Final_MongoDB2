package com.example.MongoDB2.service;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {

    ChatEntity createRoom(ChatDTO chatDTO);
    ChatEntity setMsg(ChatDTO chatDTO);
    ChatEntity getMsg(Integer roomNum);
    List<ChatDTO> getList(String name, String role);
//    Mono<ChatEntity> getRoom(String name, String name2);
}
