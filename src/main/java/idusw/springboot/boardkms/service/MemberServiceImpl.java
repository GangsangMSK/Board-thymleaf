package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .email(member.getEmail())
                .name(member.getName())
                .pw(member.getPw())
                .build();

        if (memberRepository.save(entity) != null)
            return 1;
        else
            return 0;
    }


    @Override
    public Member read(Member member) {
        MemberEntity entity = memberRepository.getById(member.getSeq());
        Member result = Member.builder()
                .seq(entity.getSeq())
                .email(entity.getEmail())
                .name(entity.getName())
                .pw(entity.getPw())
                .build();
        return result;
    }

    @Override
    public List<Member> readList() {
        List<Member > result = new ArrayList<>();
        //테이블로부터 모두 읽어와 객체의 리스트로 반환
        //entity : service <-> repository
        List<MemberEntity> entities = memberRepository.findAll(); //select * from a_memo;
        for (MemberEntity entity : entities) {
            Member member = Member.builder()
                    .seq(entity.getSeq())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .build();
            result.add(member);
        }

        return result;
    }

    @Override
    public int update(Member member) {
        return 0;
    }

    @Override
    public int delete(Member member) {
        return 0;
    }
}
