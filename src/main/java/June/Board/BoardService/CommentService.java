package June.Board.BoardService;

import June.Board.BoardEntity.Comment;
import June.Board.BoardREposit.CommentReposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CommentService {


    CommentReposit commentReposit;

    @Autowired
    public CommentService(CommentReposit commentReposit) {
        this.commentReposit = commentReposit;
    }


    public List<Comment> showComments(@PathVariable Long boardId){

        List<Comment> byBoardId = commentReposit.findByBoardId(boardId);

        return byBoardId;
    }

    public List<Comment> nickComments(@RequestParam("nickname") String nickname){

        List<Comment> byNickname = commentReposit.findByNickname(nickname);

        return byNickname;
    }








}
