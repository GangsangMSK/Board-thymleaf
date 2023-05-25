package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Board;
import idusw.springboot.boardkms.repository.BoardRepository;
import idusw.springboot.boardkms.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class BoardControllerTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void registerBoard(){
        Board board = Board.builder()
                .bno(2L)
                .title("kms")
                .content("board register")
                .writerSeq(1L)
                .writerEmail("kms@induk.ac.kr")
                .writerName("kms")
                .build();
        if(boardService.registerBoard(board) > 0) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }
}
