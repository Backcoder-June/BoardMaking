package June.Board.BoardService;

import June.Board.BoardController.boardform;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardserviceTest {

    @Autowired
    Boardservice boardservice;



    @Test
    void boardindex() {
/*

        //Expected
        Boardentity a = new Boardentity();*/
/*
        Boardentity b = new Boardentity(1L, "가가가가", "1111");
        Boardentity c = new Boardentity(1L, "가가가가", "1111");*//*

// 한방에 list 에 담는 스킬
        List<Boardentity> expected = new ArrayList<>(Arrays.asList(a));




        //Actual

        List<Boardentity> indexing = boardservice.boardindex();


        //Assert

        assertEquals(expected.toString(), indexing.toString());

        assertThat(indexing.toString()).isEqualTo(expected.toString());
*/

    }
    @Test
    void show() {

        //expected
        Boardentity board1 = new Boardentity();
        board1.setId(1L);
        board1.setTitle("RestAPI로 Patch");
        board1.setContents("들어갈것인가");

        //actual
        Boardentity show1 = boardservice.show(1L);


        //assert

        assertThat(show1.toString()).isEqualTo(board1.toString());
    }

    @Test
    void show_noid() {

        //expected
        Boardentity board999 = null;

        //actual
        Boardentity show999 = boardservice.show(-1L);


        //assert
        assertThat(show999).isEqualTo(board999);
    }



    @Test
    void create_title_contents_only_dto() {
        //expected

        String title = "가가가가";
        String contents = "1111";
        boardform dto = new boardform();
        dto.setId(null);
        dto.setTitle(title);
        dto.setContents(contents);
        Boardentity expected = new Boardentity();
        expected.setId(256L);
        expected.setTitle(title);
        expected.setContents(contents);

        //actual
        Boardentity create1 = boardservice.create(dto);


        //assert

        assertThat(create1.toString()).isEqualTo(expected.toString());

    }











    @Test
    void create_id_included_dto() {            //create 할때 id 는 안주는데, 줘버리는 경우 에러
        //expected                              // if (boardentity.getid() != null) return null; 설정해뒀기때문에
        Long id = 255L; //있는id                 //null 리턴 예상
        String title = "가가가가";
        String contents = "1111";

        boardform dto = new boardform();
        dto.setId(id);
        dto.setTitle(title);
        dto.setContents(contents);

        Boardentity expected = null;              // null 예상

        //actual
        Boardentity create1 = boardservice.create(dto);


        //assert

        assertThat(create1).isEqualTo(expected);

    }


    @Test
//    @Transactional
    void edit_성공_id_title_content_다존재_dto입력() {


        //expected
        Long id = 1L;
        String title = "가가가가";
        String contents = "1111";

        boardform dto = new boardform(id, title, contents);

        dto.toEntity();

        Boardentity expected = new Boardentity(id, title, contents);

        //actual

        Boardentity edited = boardservice.edit(id, dto);


        //assert

        assertThat(edited.toString()).isEqualTo(expected.toString());
    }

    @Test
//    @Transactional
    void edit_성공_id_title_존재_두개만존재dto() {
        //expected
        Long id = 1L;
        String title = "나나나나";

        boardform dto = new boardform(id, title, null);

        dto.toEntity();

        Boardentity expected = new Boardentity(id, title, "1111");

        //actual

        Boardentity edited = boardservice.edit(id, dto);


        //assert

        assertThat(edited.toString()).isEqualTo(expected.toString());
    }




    @Test
    @Transactional
    void edit_실패_존재하지않는id_dto입력() {

        //expected
        Long id = 3L;
        String title = "다다다다";
        String contents = "3333";

        boardform dto = new boardform(id, title, contents);

        dto.toEntity();

        Boardentity expected = null;

        //actual

        Boardentity edited = boardservice.edit(id, dto);


        //assert

        assertThat(edited).isEqualTo(expected);
    }








    @Test
    void edit_실패_id만_존재_dto() {                     //성공 되는데. id만 줘도, patch로 title contents 받아오니까
                                                            //위에 id,title만 줬을때 성공인것과 마찬가지로 성공 이어야할거같은데
        //expected
        Long id = 1L;
        String title = "나나나나";
        String contents = "1111";

        boardform dto = new boardform(id, null, null);

        dto.toEntity();

        Boardentity expected = new Boardentity(id, title, contents);

        //actual

        Boardentity edited = boardservice.edit(id, dto);


        //assert

        assertThat(edited).isEqualTo(expected);
    }

    @Test
    void delete_성공_id존재() {

        //expected
        Boardentity expected = null;

        //actual
        Boardentity deleted = boardservice.delete(254L);

        Boardentity show = boardservice.show(254L);

        //assert
        assertThat(show).isEqualTo(expected);





    }
    @Test
    @Transactional
    void delete_실패_id없음() {


        //expected
        Boardentity expected = null;


        //actual

        Boardentity deleted = boardservice.delete(3L);


        //assert

        assertThat(deleted).isEqualTo(expected);

    }




    //
    }