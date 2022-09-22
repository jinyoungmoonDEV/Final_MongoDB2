package com.example.MongoDB2.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "auto_sequence")
public class AutoIncrementSequence {
    @Id
    private String id;
    private String sig; //정보 불러오기 위한 Signature
    private Integer seq; //새로운 채팅방 번호
}
