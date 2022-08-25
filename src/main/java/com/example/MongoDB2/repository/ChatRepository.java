package com.example.MongoDB2.repository;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
    @Query("{room:?0}")
    ChatEntity mFindByRoom(Integer room);
    @Query("{user:?0}")
    List<ChatDTO> mFindByUser(String user);
    @Query("{gosu:?0}")
    List<ChatDTO> mFindByGosu(String gosu);
    @Query("{user:?0,gosu:?1}")
    ChatEntity mFindByUserAndGosu(String user, String gosu);
}
