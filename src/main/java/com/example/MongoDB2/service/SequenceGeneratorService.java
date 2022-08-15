package com.example.MongoDB2.service;

import com.example.MongoDB2.domain.AutoIncrementSequence;
import com.example.MongoDB2.repository.AIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class SequenceGeneratorService {

    private final AIRepository repository;

    public int getAI(){
        AutoIncrementSequence ai = repository.findBySig("ai");
        Integer seq = ai.getSeq();
        Integer result = seq + 1;
        AutoIncrementSequence input = AutoIncrementSequence.builder()
                .id(ai.getId())
                .sig(ai.getSig())
                .seq(result)
                .build();
        repository.save(input);
        return result;
    }
}
