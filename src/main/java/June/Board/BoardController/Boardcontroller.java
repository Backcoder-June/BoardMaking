package June.Board.BoardController;

import June.Board.BoardEntity.Comment;
import June.Board.BoardService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/*

@Controller
public class Boardcontroller {

    private final CommentService commentService;
    @Autowired
    public Boardcontroller(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/board/{id}")
    public String userComments(@PathVariable Long id, Model model){

        List<Comment> allcomments = commentService.showComments(id);

        model.addAttribute("commentkey", allcomments);

        return "Baord/allcomment";

    }










}
*/
