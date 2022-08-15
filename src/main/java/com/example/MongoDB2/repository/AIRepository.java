package com.example.MongoDB2.repository;

import com.example.MongoDB2.domain.AutoIncrementSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AIRepository extends MongoRepository<AutoIncrementSequence, String> {
    @Query("{sig:?0}")
    AutoIncrementSequence findBySig(String sig);
}
