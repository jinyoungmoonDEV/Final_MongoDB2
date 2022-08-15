package com.example.MongoDB2.domain.dto;

import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatDTO {
    private String id;
    private String user;
    private String gosu;
    private Integer room;
    private List<Info> info;

    public ChatEntity toEntity(){
        ChatEntity chatEntity = ChatEntity.builder()
                .id(id)
                .user(user)
                .gosu(gosu)
                .room(room)
                .info(info)
                .build();
        return chatEntity;
    }
}
