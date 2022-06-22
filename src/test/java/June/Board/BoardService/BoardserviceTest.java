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
        Boardentity show999 = boardservice.show(999L);

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
    void edit() {
    }

    @Test
    void delete() {
    }




    //
    }