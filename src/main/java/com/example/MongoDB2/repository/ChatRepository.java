package com.example.MongoDB2.repository;

import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
    @Query("{room:?0}")
    ChatEntity mFindByRoom(Integer room);
    @Query("{user:?0}")
    ChatEntity mFindByUser(String user);
    @Query("{gosu:?0}")
    ChatEntity mFindByGosu(String gosu);

}
