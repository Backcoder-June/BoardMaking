package June.Board.BoardController;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardService.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Servicecontroller {

    private final Boardservice boardservice;

    @Autowired
    public Servicecontroller(Boardservice boardservice) {
        this.boardservice = boardservice;
    }


    @GetMapping("/service/allboard")
    public List<Boardentity> list() {
        return boardservice.boardindex();
    }

    @GetMapping("/service/showboard/{id}")
    public Boardentity mywrite(@PathVariable Long id){
        return boardservice.show(id);
    }

    @PostMapping("/service/allboard")
    public ResponseEntity<Boardentity> writing(@RequestBody boardform dto){
        Boardentity written = boardservice.create(dto);
        return (written != null)?
                         ResponseEntity.status(HttpStatus.OK).body(written):
                         ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }






}
