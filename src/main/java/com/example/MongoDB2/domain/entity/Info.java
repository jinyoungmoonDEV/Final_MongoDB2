package com.example.MongoDB2.domain.entity;

import com.example.MongoDB2.domain.dto.ChatDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Info {
    private String msg;
    private String user;
    private String gosu;
    private String createdAt;
}
