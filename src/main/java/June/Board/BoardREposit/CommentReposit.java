package June.Board.BoardREposit;

import June.Board.BoardEntity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentReposit extends JpaRepository<Comment, Long> {

    //게시글 전체 댓글 가져오기

    @Query(value = "SELECT * FROM comment WHERE board_id = :boardId", nativeQuery = true)
    List<Comment> findByBoardId(@Param("boardId") Long boardId);

    // 특정 닉네임의 모든 댓글 가져오기

//    @Query(value = "SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(String nickname);












}
