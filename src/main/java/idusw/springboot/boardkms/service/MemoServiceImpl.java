package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Memo;
import idusw.springboot.boardkms.entity.MemoEntity;
import idusw.springboot.boardkms.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service(value = "Impl") // @Service : stereotype, Spring Framework에게 컴포넌트임을 알려줌
public class MemoServiceImpl implements MemoService {
    MemoRepository memoRepository;
    public MemoServiceImpl(MemoRepository memoRepository) { // 생성자 주입 (Constructor Injection)
        // Spring Framework가 MemoRepository 인터페이스를 구현한 인스턴스를 배정함
        this.memoRepository = memoRepository;
    }

    @Override
    public int create(Memo m) {
        return 0;
    }

    @Override
    public Memo read(Memo memo) {
        //테이블로부터 하나 읽어와 객체로 반환
        //entity : service <-> repository
        MemoEntity entity = memoRepository.findById(memo.getMNo()).get();
        Memo result = new Memo(); // domain 객체 DTO(Data Transfer Object) - controller <-> service, controller <-> view
        result.setMNo(entity.getMNo());
        result.setMemoText(entity.getMemoText());

        return result;
    }

    @Override
    public List<Memo> readList() {

        List<Memo> result = new ArrayList<>();
        //테이블로부터 모두 읽어와 객체의 리스트로 반환
        //entity : service <-> repository
        List<MemoEntity> entities = memoRepository.findAll(); //select * from a_memo;
        for (MemoEntity entity : entities) {
            Memo memo = new Memo(); // domain 객체 DTO(Data Transfer Object) - controller <-> service, controller <-> view
            memo.setMNo(entity.getMNo());
            memo.setMemoText(entity.getMemoText());
            result.add(memo);
        }

        return result;
    }

    @Override
    public int update(Memo memo) {
        return 0;
    }

    @Override
    public int delete(Memo memo) {
        return 0;
    }

    @Override
    public List<Memo> initailize() {

        //테이블 초기화 코드
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("mskim" + i).build();
            memoRepository.save(memo);
        });

        return readList();
    }
}
