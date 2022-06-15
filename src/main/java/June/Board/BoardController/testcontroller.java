package June.Board.BoardController;

import June.Board.BoardEntity.BackMember;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.MarketerEntity;
import June.Board.BoardEntity.modelentity;
import June.Board.BoardREposit.Boardreposit;
import June.Board.BoardREposit.Marketerreposit;
import June.Board.BoardREposit.Memberreposit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class testcontroller {

    private final Boardreposit boardreposit;
    private final Marketerreposit marketerreposit;
    private final Memberreposit memberreposit;


    @Autowired
    public testcontroller(Boardreposit boardreposit, Marketerreposit marketerreposit, Memberreposit memberreposit) {
        this.boardreposit = boardreposit;
        this.marketerreposit = marketerreposit;
        this.memberreposit = memberreposit;
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

        log.info("id= " + id);

//        Optional<Boardentity> boardentity = boardreposit.findById(id);
//        Boardentity boardentity = boardreposit.findById(id).orElse(null);
        Optional<Boardentity> boardentity = boardreposit.findById(id);

        model.addAttribute("boardkey", boardentity.orElse(null));

        return "Board/show";
    }
    @GetMapping("/board/all")
    public String allboard(Model model){
        List<Boardentity> reuslt = (ArrayList<Boardentity>) boardreposit.findAll();

        model.addAttribute("resultkey", reuslt);

        return "Board/showall";
    }

    @GetMapping("/board/{id}/edit/")
    public String editpage (@PathVariable Long id, Model model){

        Boardentity editentity = boardreposit.findById(id).orElse(null);

        model.addAttribute("editkey", editentity);

        return "Board/boardedit";
    }

    @PostMapping("/board/")
    public String editlink(){

        return "";


    }





    //
}
