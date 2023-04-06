package iducs.springboot.boardthymeleaf.service;

import iducs.springboot.boardthymeleaf.domain.Memo;

import java.util.List;

public interface MemoService {

    int create(Memo memo);
    Memo read(Memo memo);
    List<Memo> readList();
    int update(Memo memo);
    int delete(Memo memo);
    List<Memo> initailize();
}
