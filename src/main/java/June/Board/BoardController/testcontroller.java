package June.Board.BoardController;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class testcontroller {

    private final Boardreposit boardreposit;

    @Autowired
    public testcontroller(Boardreposit boardreposit) {
        this.boardreposit = boardreposit;
    }

    private String username = "June";
    private String date = "2022-06-07";
    private String marketername = "Tom";

    @GetMapping("/")
    public String meet(Model model) {
        model.addAttribute("username", username);

        model.addAttribute("date", date);
        return "Greetings";
    }


    @GetMapping("/farewell")
    public String farewell(Model model) {

        model.addAttribute("username", username);
        model.addAttribute("marketer", marketername);
        return "Farewell";
    }

    @GetMapping("/starter")
    public String starter(Model model){
        model.addAttribute("marketer", marketername);
        model.addAttribute("username", username);

        return "startertemplate";
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
        return "redirect:/board";
    }

    //
}
