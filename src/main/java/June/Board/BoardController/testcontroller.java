package June.Board.BoardController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testcontroller {
    private String username = "June";
    private String date = "2022-06-07";
    private String marketername = "Tom";

    @GetMapping("/")
    public String meet(Model model){
        model.addAttribute("username", username);

        model.addAttribute("date", date);
        return "Greetings";
    }


    @GetMapping("/farewell")
    public String farewell(Model model){

        model.addAttribute("username", username);
        model.addAttribute("marketer", marketername);
        return "Farewell";
    }



    //
}
