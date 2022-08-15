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
    private String sig;
    private Integer seq;
}
