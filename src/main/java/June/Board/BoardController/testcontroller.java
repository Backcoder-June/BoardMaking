package June.Board.BoardController;

import June.Board.BoardEntity.*;
import June.Board.BoardREposit.Boardreposit;
import June.Board.BoardREposit.CommentReposit;
import June.Board.BoardREposit.Marketerreposit;
import June.Board.BoardREposit.Memberreposit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class testcontroller {

    private final Boardreposit boardreposit;
    private final Marketerreposit marketerreposit;
    private final Memberreposit memberreposit;
    private final CommentReposit commentReposit;

    @Autowired
    public testcontroller(Boardreposit boardreposit, Marketerreposit marketerreposit, Memberreposit memberreposit, CommentReposit commentReposit) {
        this.boardreposit = boardreposit;
        this.marketerreposit = marketerreposit;
        this.memberreposit = memberreposit;
        this.commentReposit = commentReposit;
    }


/*private String username = "June";
    private String date = "2022-06-07";
    private String marketername = "Tom";
*/

      modelentity modelEntity= new modelentity();

    @GetMapping("/")
    public String meet(Model model) {
        model.addAttribute("username", modelEntity.getUsername() );

        model.addAttribute("date", modelEntity.getNowdate());
        return "Greetings";
    }


    @GetMapping("/farewell")
    public String farewell(Model model) {

        model.addAttribute("username", modelEntity.getUsername());
        model.addAttribute("marketer", modelEntity.getMarketer());
        return "Farewell";
    }

    @GetMapping("/starter")
    public String starter(Model model){
        model.addAttribute("marketer", modelEntity.getMarketer());
        model.addAttribute("username", modelEntity.getUsername());

        return "startertemplate";
    }

    @PostMapping("/farewell")
    public String marketer(marketerform markform){
        MarketerEntity MK = new MarketerEntity();
        MK.setCounsel_category(markform.getCounsel_category());
        MK.setCounsel_content(markform.getCounsel_content());
        MK.setCounsel_unusual(markform.getCounsel_unusual());

        marketerreposit.save(MK);
        log.info(markform.toString());

        return "redirect:/farewell";
    }

    @GetMapping("/board")
    public String boardinput(){
        return "Board/Board";}

    @PostMapping("/board")
    public String getinput(boardform form){

        //form 은 현재 Entity 가 아니다. boardentity 는 entity이다.
        // -> form 값을 boardentity 에 담고, boardentity 를 사용하면, entity 를 사용하는게 된다.
        // form 이 entity 가 될 필요가 없다.
        Boardentity boardentity = new Boardentity();
        boardentity.setTitle(form.getTitle());
        boardentity.setContents(form.getContents());

        Boardentity saved = boardreposit.save(boardentity);

        //   System.out.println(form.toString());
        log.info(form.toString());
        return "redirect:/board/" + saved.getId();
    }

    @GetMapping("/login")
    public String logging(){
        return "Member/memberlogin";
    }
    @PostMapping("/")
    public String savemember(Memberform memberform){
        BackMember backMember = new BackMember();
        backMember.setUserid(memberform.getUserid());
        backMember.setUserpw(memberform.getUserpw());
// Bind exception 잡는법 보충 필요 안잡힘
        try {
        memberreposit.save(backMember);}

        catch (IllegalStateException e){
            return "Member/loginerror";
        }

        log.info(memberform.toString());

        return "redirect:/";
    }


    @GetMapping("/board/{id}")
    public String userpage(@PathVariable Long id, Model model){

//        log.info("id= " + id);

//        Optional<Boardentity> boardentity = boardreposit.findById(id);
//        Boardentity boardentity = boardreposit.findById(id).orElse(null);
        Optional<Boardentity> boardentity = boardreposit.findById(id);

        model.addAttribute("boardkey", boardentity.orElse(null));

        List<Comment> commentlist = commentReposit.findByBoardId(id);
        model.addAttribute("commentkey", commentlist);

        return "Board/show";
    }
    @GetMapping("/board/all")
    public String allboard(Model model){
        List<Boardentity> result = (ArrayList<Boardentity>) boardreposit.findAll();

        // DB 에서 직접 수정하다가 null 값 entity 가 들어가면, findall null 값 뜬걸 인식 못해서 오류가 뜬다.
        // 어떻게 잡을까   => 애초에 null 값이 입력되지 않게 설계한다.
        // => .patch ( ) 메소드로, 수정 데이터를 this 데이터로 보내서, 고쳐진건 고쳐진 this 로, 없는건 원래 this 가 들어가게 해서
        // null 값은 존재하지 않게 만든다.


        model.addAttribute("resultkey", result);

        return "Board/showall";
    }

    @GetMapping("/board/{id}/edit/")
    public String editpage (@PathVariable Long id, Model model){

        Boardentity editentity = boardreposit.findById(id).orElse(null);

        model.addAttribute("editkey", editentity);

        return "Board/boardedit";
    }

    @PostMapping("/board/{id}/edit/")
    public String editlink(boardform form2){

        log.info(form2.toString());
        Boardentity boardentity = new Boardentity();
        boardentity.setId(form2.getId());
        boardentity.setTitle(form2.getTitle());
        boardentity.setContents(form2.getContents());

        // 먼저찾아서 있으면 edit 된걸로 저장 해라. < 없을때 오류 방지
         Boardentity BE = boardreposit.findById(boardentity.getId()).orElse(null);

         if(BE != null){

             Boardentity result2 = boardreposit.save(boardentity);

             log.info(result2.toString());
         }

         //리다이렉트
        return "redirect:/board/" + boardentity.getId();


    }


    @GetMapping("/board/{id}/delete/")
    public String delete(@PathVariable Long id, RedirectAttributes RA){
//        log.info("삭제요청");

        // 삭제할 대상 엔티티 가져오기
        Boardentity deleteboard = boardreposit.findById(id).orElse(null);

        log.info(deleteboard.toString());
        // 삭제하기
        if (deleteboard!=null){
            boardreposit.delete(deleteboard);

            RA.addFlashAttribute("msg", "삭제 되었습니다.");
        }

        return "redirect:/board/all/";

    }



    //
}
