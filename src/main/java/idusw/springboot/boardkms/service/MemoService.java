package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Memo;

import java.util.List;

public interface MemoService {

    int create(Memo memo);
    Memo read(Memo memo);
    List<Memo> readList();
    int update(Memo memo);
    int delete(Memo memo);
    List<Memo> initailize();
}
