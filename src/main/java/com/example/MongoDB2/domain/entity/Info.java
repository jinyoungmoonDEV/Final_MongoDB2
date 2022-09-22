package com.example.MongoDB2.domain.entity;

import com.example.MongoDB2.domain.dto.ChatDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Info {
    private String msg; //메세지
    private String user; //사용자
    private String gosu; //전문가
    private String createdAt; //생성 날짜
}
