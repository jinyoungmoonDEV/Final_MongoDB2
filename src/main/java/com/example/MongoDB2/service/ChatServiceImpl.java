package com.example.MongoDB2.service;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import com.example.MongoDB2.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatService{

    private final ChatRepository chatRepository;

    private final SequenceGeneratorService seq;

    @Override
    public ChatEntity createRoom(ChatDTO chatDTO) {
        chatDTO.setRoom(seq.getAI());
        return chatRepository.save(chatDTO.toEntity());
    }
    @Override
    public ChatEntity setMsg(ChatDTO chatDTO) {
        ChatEntity chat = chatRepository.mFindByRoom(chatDTO.getRoom());
        ChatDTO input = chat.toDTO();

        List info = input.getInfo();
        info.add(chatDTO.getInfo());
        input.setInfo(info);

        return chatRepository.save(input.toEntity());
    }

    @Override
    public ChatEntity getMsg(Integer room) {
        return chatRepository.mFindByRoom(room);
    }

    @Override
    public List<ChatDTO> getList(String name, String role) {
        if (role.equals("ROLE_USER")){
            ChatEntity chat = chatRepository.mFindByUser(name); //유저 이름으로 된 채팅 정보 전부 가져온다 채팅이 여러개면 List에 담아짐

            List<ChatDTO> chatList = new ArrayList<>();//find문 결과 값 인덱스로 뽑기 위해 담을 리스트 생성
            chatList.add(chat.toDTO());//리스트에 find문 결과값 주입

            List<Info> valueList;//info에서 인덱스 0번값 담기 위한 리스트
            List<ChatDTO> result = new ArrayList<>();//최종 리턴값 담을 리스트

            for (int i = 0; i < chatList.size(); i++){
                valueList = new ArrayList<>();//반복 할떄마다 새로지정
                result = new ArrayList<>();//반복 할떄마다 새로지정

                ChatDTO input = chatList.get(i);//리스트에서 순서대로 뽑기
                Info value = input.getInfo().get(0);//info에서 인덱스 0번 값 담기

                valueList.add(value);//담은값 리스트로 타입 맞춰준다

                ChatDTO last = ChatDTO.builder()//builder로 새로운 DTO에 위에서 추출한  info list 와 기존 값들을 불러와 build
                        .id(input.getId())
                        .user(input.getUser())
                        .gosu(input.getGosu())
                        .room(input.getRoom())
                        .info(valueList)
                        .build();

                result.add(last);//build한 값 리턴할 리스트에 담아준다
            }

            return result;
        }
        else if (role.equals("ROLE_GOSU")){
            ChatEntity chat = chatRepository.mFindByGosu(name);

            List<ChatDTO> chatList = new ArrayList<>();
            chatList.add(chat.toDTO());

            List<Info> valueList = new ArrayList<>();
            List<ChatDTO> result = new ArrayList<>();

            for (int i = 0; i < chatList.size(); i++){
                valueList = new ArrayList<>();
                result = new ArrayList<>();

                ChatDTO input = chatList.get(i);
                Info value = input.getInfo().get(0);

                valueList.add(value);

                ChatDTO last = ChatDTO.builder()
                        .id(input.getId())
                        .user(input.getUser())
                        .gosu(input.getGosu())
                        .room(input.getRoom())
                        .info(valueList)
                        .build();

                result.add(last);
            }

            return result;
        }
        else return null;
    }
}
