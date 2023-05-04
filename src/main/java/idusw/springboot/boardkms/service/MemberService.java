package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    int create(Member member);
    Member read(Member member);
    List<Member> readList();
    int update(Member member);
    int delete(Member member);
    Member login(Member member);


    PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO);

    //java 1.8 : 인터페이스가 기본 메소드를 가질 수 있도록 함.
    default MemberEntity dtoToEntity(Member dto){ // dto -> entity, service -> repository
        MemberEntity entity = MemberEntity.builder()
                .seq(dto.getSeq())
                .email(dto.getEmail())
                .name(dto.getName())
                .pw(dto.getPw())
                .build();
        return entity;

    }
    default Member entityToDto(MemberEntity entity){ // entity -> dto, repository -> service
        Member dto = Member.builder()
                .seq(entity.getSeq())
                .email(entity.getEmail())
                .name(entity.getName())
                .pw(entity.getPw())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;

    }

}
