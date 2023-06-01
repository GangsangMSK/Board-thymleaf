package idusw.springboot.boardkms.repository;

import idusw.springboot.boardkms.entity.BoardEntity;

import idusw.springboot.boardkms.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>, SearchBoardRepository {
}
