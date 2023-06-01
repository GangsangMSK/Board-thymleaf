package idusw.springboot.boardkms.service;


import idusw.springboot.boardkms.domain.Board;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.BoardEntity;
import idusw.springboot.boardkms.entity.MemberEntity;

public interface BoardService {
    int registerBoard(Board board);
    Board findBoardById(Board board);
    PageResultDTO<Board, Object[]> findBoardAll(PageRequestDTO pageRequestDTO);
    int updateBoard(Board board);
    int deleteBoard(Board board);

    default BoardEntity dtoToEntity(Board dto){
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();
        BoardEntity entity = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return entity;
    }
    default Board entityToDto(BoardEntity entity, MemberEntity memberEntity, Long replyCount){
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerSeq(memberEntity.getSeq())
                .writerName(memberEntity.getName())
                .writerEmail(memberEntity.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
