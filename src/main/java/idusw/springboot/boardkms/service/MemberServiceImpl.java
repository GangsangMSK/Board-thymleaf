package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
            //Lamda식 -> Lombok Library 사용
            Member member = Member.builder()
                    .seq(entity.getSeq())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .pw(entity.getPw())
                    .regDate(entity.getRegDate())
                    .modDate(entity.getModDate())
                    .build();
            result.add(member);
        }
        return result;
    }

    @Override
    public int update(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .email(member.getEmail())
                .name(member.getName())
                .pw(member.getPw())
                .build();
        if(memberRepository.save(entity) != null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int delete(Member member) {
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .build();
        memberRepository.deleteById(entity.getSeq());
        return 1;
    }

    @Override
    public Member login(Member member) {
        Member result = null;
        MemberEntity entitiy = memberRepository.getByEmailPw(member.getEmail(), member.getPw());
        if (entitiy != null) {
            result = Member.builder()
                    .seq(entitiy.getSeq())
                    .email(entitiy.getEmail())
                    .name(entitiy.getName())
                    .pw(entitiy.getPw())
                    .build();
        }
        return result;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("seq").ascending());

        Page<MemberEntity> result = memberRepository.findAll(pageable);
        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
