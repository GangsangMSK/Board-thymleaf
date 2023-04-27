package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Member;

import java.util.List;

public interface MemberService {
    int create(Member member);
    Member read(Member member);
    List<Member> readList();
    int update(Member member);
    int delete(Member member);

    Member login(Member member);
}
