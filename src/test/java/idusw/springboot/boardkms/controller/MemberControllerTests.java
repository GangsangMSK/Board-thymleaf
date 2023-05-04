package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.repository.MemberRepository;
import idusw.springboot.boardkms.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberControllerTests { // Unit Test : Junit - Test Framework
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void contextLoads() {
        List<Member> result = memberService.readList();
        for(Member member : result)
            System.out.println(member.getSeq() + member.getName() + member.getEmail());
    }

    @Test
    void initializeMember() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 33).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .seq(Long.valueOf(i))
                    .email("email" + i + "@induk.ac.kr")
                    .pw("pw" + i)
                    .name("name" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void testPageList(){

//        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResultDTO<Member, MemberEntity> resultDTO = memberService.getList(pageRequestDTO);
        for(Member member : resultDTO.getDtoList())
            System.out.println(member);
    }
}
