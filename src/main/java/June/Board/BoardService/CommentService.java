package June.Board.BoardService;

import June.Board.BoardController.CommentDto;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.Comment;
import June.Board.BoardREposit.Boardreposit;
import June.Board.BoardREposit.CommentReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentReposit commentReposit;
    @Autowired
    private Boardreposit boardreposit;



    public List<CommentDto> showComments(@PathVariable Long boardId){

        //조회 : 댓글목록
//        List<Comment> byBoardId = commentReposit.findByBoardId(boardId);

        //변환 : 엔티티 =>  dto
            //for 문하고, createDto 메소드 만들어서 돌리기  VS stream 문법 한방
        /*List<CommentDto> dtos = new ArrayList<CommentDto>();

        for (int i =0; i< byBoardId.size(); i++){

            Comment c = byBoardId.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/

        //반환

//        return dtos;

        return commentReposit.findByBoardId(boardId).stream().map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

        // stream 문법 익숙해져야 한다.
        // find로 찾은 comment 리스트를 stream 으로 돌려서 map 에 집어넣는다.
        // map 에는 찾아온 comment 객체를 commentDTO 로 만들거다. 만들어놓은 createCD 메소드를 이용해서.
        // 메소드에는 찾아온 그 comment를 넣으면 commentDTO 로 만들어 주는 것


    }

    public List<Comment> nickComments(@RequestParam("nickname") String nickname){

        List<Comment> byNickname = commentReposit.findByNickname(nickname);

        return byNickname;
    }


    @Transactional   // DB를 건드리는작업 - 롤백될 수 있도록

    public CommentDto createComment(Long boardId, CommentDto dto) {

        //게시글 조회 및 예외

        Boardentity fBi = boardreposit.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성실패!"));
                            //없으면 -> 예외발생시키고 => 종료 시킴 //




        //댓글 엔티티 생성

        Comment comment = Comment.createMent(dto, fBi);

        // 댓글엔티티 DB로 저장

        Comment savedcomment = commentReposit.save(comment);

        //DTo 로 변경반환
        return CommentDto.createCommentDto(savedcomment);
    }
}
