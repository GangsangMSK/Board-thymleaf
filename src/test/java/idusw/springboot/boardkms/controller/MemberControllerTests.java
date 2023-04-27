package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberControllerTests { // Unit Test : Junit - Test Framework
    @Autowired
    MemberService memberService;

    @Test
    void contextLoads() {
        List<Member> result = memberService.readList();
        for(Member member : result)
            System.out.println(member.getSeq() + member.getName() + member.getEmail());
    }
}
