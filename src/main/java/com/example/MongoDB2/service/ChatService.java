package com.example.MongoDB2.service;

import com.example.MongoDB2.domain.dto.ChatDTO;
import com.example.MongoDB2.domain.entity.ChatEntity;
import com.example.MongoDB2.domain.entity.Info;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {

    ChatEntity newRoom(ChatDTO chatDTO); //채팅방 생성
    ChatEntity setMsg(ChatDTO chatDTO); //새로운 채팅 저장
    ChatEntity getMsg(Integer roomNum); //채팅 이력 불러오기
    List<ChatDTO> getList(String name, String role); //일반 사용지 혹은 전문가의 기존 채팅 리스트 블러오기
    List<ChatDTO> setList(List<ChatDTO> chat); //불러온 리스트에서 최신 정보만 추출하여 리스트를 교체 -> 채팅방 리스트 보여줄때 최근 채팅 메세지도 같이 서비스 하기 위하여
}
