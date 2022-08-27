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
        public ChatEntity createRoom(ChatDTO chatDTO) {

            String user = chatDTO.getUser();
            String gosu = chatDTO.getGosu();

            if (chatRepository.mFindByUserAndGosu(user, gosu) != null){
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "ChatRoom Already Exist");
            }

            else {
                List<Info> input = new ArrayList<>();

                Info empty = Info.builder()
                        .msg(null)
                        .user(null)
                        .gosu(null)
                        .createdAt(null)
                        .build();

                input.add(empty);

                chatDTO.setRoom(seq.getAI());
                chatDTO.setInfo(input);

                return chatRepository.save(chatDTO.toEntity());
            }
        }
        @Override
        public ChatEntity setMsg(ChatDTO chatDTO) {
            ChatEntity chat = chatRepository.mFindByRoom(chatDTO.getRoom());

            ChatDTO input = chat.toDTO();

            List<Info> list = input.getInfo();
            Info info = chatDTO.getInfo().get(0);
            list.add(info);

            input.setInfo(list);

            return chatRepository.save(input.toEntity());
        }

        @Override
        public ChatEntity getMsg(Integer room) {
            return chatRepository.mFindByRoom(room);
        }

        @Override
        public List<ChatDTO> getList(String name, String role) {
            if (role.equals("ROLE_USER")){

                List<ChatDTO> chat = chatRepository.mFindByUser(name); //유저 이름으로 된 채팅 정보 전부 가져온다

                List<Info> valueList;//info에서 인덱스 0번값 담기 위한 리스트

                List<ChatDTO> result = new ArrayList<>();//최종 리턴값 담을 리스트

                for (int i = 0; i < chat.size(); i++){
                    valueList = new ArrayList<>();//반복 할떄마다 새로지정

                    ChatDTO input = chat.get(i);//리스트에서 순서대로 뽑기

                    Integer index = input.getInfo().size()-1;

                    Info value = input.getInfo().get(index);//info에서 마지막 인덱스 값 담기

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

                List<ChatDTO> chat = chatRepository.mFindByGosu(name);

                List<Info> valueList;//info에서 인덱스 0번값 담기 위한 리스트

                List<ChatDTO> result = new ArrayList<>();//최종 리턴값 담을 리스트

                for (int i = 0; i < chat.size(); i++){
                    valueList = new ArrayList<>();//반복 할떄마다 새로지정

                    ChatDTO input = chat.get(i);//리스트에서 순서대로 뽑기

                    Integer index = input.getInfo().size()-1;

                    Info value = input.getInfo().get(index);//info에서 인덱스 0번 값 담기

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
            else return null;
        }
    }
