package com.example.MongoDB2.service;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import com.example.MongoDB2.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatServiceImpl implements ChatService{


    private final ChatRepository chatRepository;


    private final SequenceGeneratorService seq;


    @Override
    public ChatEntity newRoom(ChatDTO chatDTO) {
        String user = chatDTO.getUser();
        String gosu = chatDTO.getGosu();

        if (chatRepository.mFindByUserAndGosu(user, gosu) != null){ //이미 채팅방이 있을경우
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "ChatRoom Already Exist");
        }

        else {// NPE 방지
            List<Info> input = new ArrayList<>(); //리스트

            Info empty = Info.builder() //빈 정보
                    .msg(null)
                    .user(null)
                    .gosu(null)
                    .createdAt(null)
                    .build();

            input.add(empty); //추가

            chatDTO.setRoom(seq.getAI()); //채팅방 번호 발급
            chatDTO.setInfo(input); //빈 정보

            return chatRepository.save(chatDTO.toEntity());
        }
    }
    @Override
    public ChatEntity setMsg(ChatDTO chatDTO) {
        ChatEntity chat = chatRepository.mFindByRoom(chatDTO.getRoom()); //방 번호로 채팅방 찾는다

        ChatDTO input = chat.toDTO();

        List<Info> list = input.getInfo(); //기존 채팅 정보 리스트
        Info info = chatDTO.getInfo().get(0); //새로입력된 채팅 정보
        list.add(info); //추가

        input.setInfo(list); //추가된 정보로 교체

        return chatRepository.save(input.toEntity());
    }

    @Override
    public ChatEntity getMsg(Integer room) {
            return chatRepository.mFindByRoom(room);
        }

    @Override
    public List<ChatDTO> getList(String name, String role) {
        if (role.equals("ROLE_USER")){
            List<ChatDTO> chat = chatRepository.mFindByUser(name); //일반 사용자 이름으로 된 채팅 정보 전부 가져온다
            return setList(chat);
        }
        else if (role.equals("ROLE_GOSU")){
            List<ChatDTO> chat = chatRepository.mFindByGosu(name); //전문가 이름으로 된 채팅 정보 전부 가져온다
            return setList(chat);
        }
        else return null;
    }

    @Override
    public List<ChatDTO> setList(List<ChatDTO> chat) {

        List<Info> valueList;//info에서 인덱스 0번값 담기 위한 리스트

        List<ChatDTO> result = new ArrayList<>();//최종 리턴값 담을 리스트

        for (int i = 0; i < chat.size(); i++){
            valueList = new ArrayList<>();//반복 할떄마다 새로지정

            ChatDTO input = chat.get(i);//리스트에서 순서대로 뽑기

            Integer index = input.getInfo().size()-1; //index

            Info value = input.getInfo().get(index);//info에서 마지막 인덱스 값 담기

            valueList.add(value);//담은값 리스트로 타입 맞춰준다

            input.setInfo(valueList); //변경된 Info만 교체

            result.add(input);//build한 값 리턴할 리스트에 담아준다
        }

        return result;
    }
}
