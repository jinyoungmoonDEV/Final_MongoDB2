package com.example.MongoDB2.domain.entity;

import com.example.MongoDB2.domain.dto.ChatDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Document(collection = "chat")
public class ChatEntity {
    @Id
    private String id;
    private String user;
    private String gosu;
    private Integer room;
    private List<Info> info;

    public ChatDTO toDTO() {
        ChatDTO chatDTO = ChatDTO.builder()
                .id(id)
                .user(user)
                .gosu(gosu)
                .room(room)
                .info(info)
                .build();
        return chatDTO;
    }
}
