package June.Board.BoardController;

import June.Board.BoardService.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;

    //댓글 목록 조회

    @GetMapping("/api/allboard/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> comments (@PathVariable Long boardId){

        List<CommentDto> dtos = commentService.showComments(boardId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성

    @PostMapping("/api/allboard/{boardId}/comments")
    public ResponseEntity<CommentDto> create (@PathVariable Long boardId, @RequestBody CommentDto dto){


        CommentDto createdDto = commentService.createComment(boardId, dto);


        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }




    //댓글 수정

    //댓글 삭제











 //
}
