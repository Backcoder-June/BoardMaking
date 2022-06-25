package June.Board.BoardREposit;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositTest {

    @Autowired
    CommentReposit commentReposit;


    @DisplayName("게시글 댓글 모두조회")
    @Test
    void findByBoardId() {
          /* Case1 : 1번 게시글 댓글 모두조회 */

            // 입력 데이터 준비
            Long boardId = 1L;    //1번 게시글 확인할거니까

            // expected

            Boardentity boardentity = new Boardentity(1L, "나나나나", "1111");

            Comment comment1 = new Comment(1L, boardentity, "june", "1빠");
            Comment comment2 = new Comment(2L, boardentity, "tom", "2빠");

            List<Comment> expected = Arrays.asList(comment1, comment2);

            // actual - 이놈부터 작성
            List<Comment> allcomments = commentReposit.findByBoardId(boardId);


            // assert
            Assertions.assertThat(allcomments.toString()).isEqualTo(expected.toString());


    }

    @Test
    void findByNickname() {
    }
}