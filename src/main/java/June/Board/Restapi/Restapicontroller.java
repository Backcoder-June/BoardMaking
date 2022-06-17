package June.Board.Restapi;

import June.Board.BoardController.boardform;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Restapicontroller {

    private final Boardreposit boardreposit;

    @Autowired
    public Restapicontroller(Boardreposit boardreposit) {
        this.boardreposit = boardreposit;
    }

    @GetMapping("/api/hello")
    public String hello(){
        return "hello restapi";
    }

    @GetMapping("/api/allboard")
    public List<Boardentity> boardindex(){

        return boardreposit.findAll();
    }

    @PostMapping("/api/allboard")
     public Boardentity create(@RequestBody boardform dto){

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        return boardreposit.save(boardentity);
    }

    // 개인페이지
    @GetMapping("/api/allboard/{id}")
    public Boardentity eachboard(@PathVariable Long id){

        return boardreposit.findById(id).orElse(null);

    }


    // 개인페이지 수정
    @PatchMapping("/api/allboard/{id}")
    public ResponseEntity<Boardentity> patchboard(@PathVariable Long id, @RequestBody boardform dto){

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        Boardentity patchentity = boardreposit.findById(id).orElse(null);

        //오류처리

        if (patchentity==null || id != boardentity.getId()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Boardentity editedentity = boardreposit.save(boardentity);
        return ResponseEntity.status(HttpStatus.OK).body(editedentity);

    }







}
