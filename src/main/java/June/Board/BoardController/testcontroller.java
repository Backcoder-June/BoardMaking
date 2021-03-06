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

        //form ??? ?????? Entity ??? ?????????. boardentity ??? entity??????.
        // -> form ?????? boardentity ??? ??????, boardentity ??? ????????????, entity ??? ??????????????? ??????.
        // ????????? ???????????????, form ??????????????? toEntity ????????? ???????????????, ?????? ????????? ????????????.
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
// Bind exception ????????? ?????? ?????? ?????????
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

        // DB ?????? ?????? ??????????????? null ??? entity ??? ????????????, findall null ??? ?????? ?????? ????????? ????????? ??????.
        // ????????? ?????????   => ????????? null ?????? ???????????? ?????? ????????????.
        // => .patch ( ) ????????????, ?????? ???????????? this ???????????? ?????????, ???????????? ????????? this ???, ????????? ?????? this ??? ???????????? ??????
        // null ?????? ???????????? ?????? ?????????.


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

        // ??????????????? ????????? edit ????????? ?????? ??????. < ????????? ?????? ??????
         Boardentity BE = boardreposit.findById(boardentity.getId()).orElse(null);

         if(BE != null){

             Boardentity result2 = boardreposit.save(boardentity);

             log.info(result2.toString());
         }

         //???????????????
        return "redirect:/board/" + boardentity.getId();


    }


    @GetMapping("/board/{id}/delete/")
    public String delete(@PathVariable Long id, RedirectAttributes RA){
//        log.info("????????????");

        // ????????? ?????? ????????? ????????????
        Boardentity deleteboard = boardreposit.findById(id).orElse(null);

        log.info(deleteboard.toString());
        // ????????????
        if (deleteboard!=null){
            boardreposit.delete(deleteboard);

            RA.addFlashAttribute("msg", "?????? ???????????????.");
        }

        return "redirect:/board/all/";

    }



    //
}
