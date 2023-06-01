package idusw.springboot.boardkms.service;

import idusw.springboot.boardkms.domain.Board;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.BoardEntity;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class BoardServiceImpl implements BoardService{
    private BoardRepository boardRepository;
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

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
        return null;
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

    @Override
    public int updateBoard(Board board) {
        return 0;
    }

    @Override
    public int deleteBoard(Board board) {
        return 0;
    }
}
