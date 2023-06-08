package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Board;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.BoardEntity;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.repository.BoardRepository;
import idusw.springboot.boardkms.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository ;


    @Override
    public int registerBoard(Board board) {
        BoardEntity entity = dtoToEntity(board);

        if (boardRepository.save(entity) != null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Board findBoardById(Board board) {
        Object[] objects = (Object[]) boardRepository.getBoardByBno(board.getBno());
        return entityToDto((BoardEntity) objects[0], (MemberEntity) objects[1], (Long) objects[2]);
    }

    @Override
    public PageResultDTO<Board, Object[]> findBoardAll(PageRequestDTO pageRequestDTO) {
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        Function<Object[], Board> fn = (entity -> entityToDto((BoardEntity) entity[0], (MemberEntity) entity[1], (Long) entity[2]));
        return new PageResultDTO<>(result, fn, 10);
    }

    @Transactional
    @Override
    public int updateBoard(Board board) {
        boardRepository.save(dtoToEntity(board));
        return 0;
    }

    @Transactional
    @Override
    public int deleteBoard(Board board) {
        replyRepository.deleteByBno(board.getBno());
        boardRepository.deleteById(board.getBno());
        return 0;
    }
}
