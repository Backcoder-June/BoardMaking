package June.Board.BoardController;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardService.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public Boardentity mywrite(@PathVariable Long id) {
        return boardservice.show(id);
    }

    @PostMapping("/service/allboard")
    public ResponseEntity<Boardentity> writing(@RequestBody boardform dto) {
        Boardentity written = boardservice.create(dto);
        return (written != null) ?
                ResponseEntity.status(HttpStatus.OK).body(written) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PatchMapping("/service/showboard/{id}")
    public ResponseEntity<Boardentity> update (@PathVariable Long id, @RequestBody boardform dto){

        Boardentity updated = boardservice.edit(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }


    @DeleteMapping("/service/showboard/{id}")
    public ResponseEntity<Boardentity> delete (@PathVariable Long id){

        Boardentity deletetarget = boardservice.show(id);


        return (deletetarget == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) :
                ResponseEntity.status(HttpStatus.OK).body(boardservice.delete(id));

    }

    @DeleteMapping("/service/showboard/another/{id}")
    public ResponseEntity<Void> deleting(@PathVariable Long id) {

        Boardentity deleted = boardservice.delete(id);

        return (deleted!=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

//    @PostMapping("/service/transaction")
//    public ResponseEntity<List<Boardentity>> transaction (@RequestBody List<boardform> dtos){
//
//        List<Boardentity> createdList = boardservice.creating(dtos);
//
//        return (createdList != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(createdList) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }






    }

