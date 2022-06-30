package June.Board.BoardREposit;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

//        System.out.println(allcomments.toString());
            // assert
            assertThat(allcomments.toString()).isEqualTo(expected.toString());


    }


    @Test
    void findByBoardId2() {
        /* Case2 : 존재하지 않는 게시글 댓글 모두조회 */

        // 입력 데이터 준비
        Long boardId = 100L;

        // expected
        List<Comment> expected = Arrays.asList();

        // actual - 이놈부터 작성
        List<Comment> allcomments = commentReposit.findByBoardId(boardId);

        // assert
        assertThat(allcomments.toString()).isEqualTo(expected.toString());


    }



    @Test
    void findByNickname() {
        // june 의 모든 댓글 조회


        //data

        String nickname = "준";
        Boardentity boardentity1 = new Boardentity(1L, "가가가가", "1111");
        Boardentity boardentity2 = new Boardentity(2L, "나나나나", "2222");
        Boardentity boardentity3 = new Boardentity(3L, "다다다다", "3333");

        //expected
        Comment comment1 = new Comment(1L, boardentity1, "준", "ㅎㅎ");
        Comment comment2 = new Comment(3L, boardentity2, "준", "ㅎㅎ");
        Comment comment3 = new Comment(4L, boardentity3, "준", "ㅎㅎ");
        Comment comment4 = new Comment(5L, boardentity3, "준", "ㅎㅎ");

        List<Comment> expected = Arrays.asList(comment1, comment2, comment3, comment4);


        //actual

        List<Comment> byNickname = commentReposit.findByNickname(nickname);

        //assert

        assertThat(byNickname.toString()).isEqualTo(expected.toString());


    }
    @Test
    void findByNickname_null() {
        // nickname 값 null 일때  

        //data

        String nickname;

        //expected
        List<Comment> expected = Arrays.asList();


        //actual

        List<Comment> byNickname = commentReposit.findByNickname(null);

        //assert


        assertThat(byNickname).isEqualTo(expected);

        

    }

    @Test
    void findByNickname_nothing() {
        // nickname 값 "" 일때

        //data

        String nickname = "";

        //expected
        List<Comment> expected = Arrays.asList();


        //actual

        List<Comment> byNickname = commentReposit.findByNickname(nickname);

        //assert


        assertThat(byNickname).isEqualTo(expected);
    }





    // "asvsadf" 인 닉네임 모두 조회
    @Test
    void findByNickname_asdf() {
        // nickname 값 asdfqwer 없는 아이디 일때

        //data

        String nickname = "sdavqaf";

        //expected
        List<Comment> expected = Arrays.asList();


        //actual

        List<Comment> byNickname = commentReposit.findByNickname(nickname);

        //assert


        assertThat(byNickname).isEqualTo(expected);
    }


    /**  nickname     1.null 일때랑
     *                2."" 일때랑
     *                3.asfdaqq 없는 nick 일때랑
     *                => 모두  null 값으로 나온다. **/




}