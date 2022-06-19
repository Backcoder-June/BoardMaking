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
    public ResponseEntity<Boardentity>patchboard(@PathVariable Long id, @RequestBody boardform dto){

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        Boardentity patchentity = boardreposit.findById(id).orElse(null);

        //오류처리

        if (patchentity==null || id != boardentity.getId()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // restAPI 등으로 데이터 입력하다보면, 특정 필드만 null 값이 되는 경우가 나올 수 있음
        // 위에 오류처리로, patchentity 전체가 null 인건 잡았지만, 그 중 필드 한두개가 빵꾸난건, 못잡았다.
        // 이거 못잡으면, title, content 등 일부가 빵꾸난 객체가 생길시, findall 할 때 오류나서 whitelabel 뜬다.


        //save 하기 전에, 수정하기 전의 entity data 붙여주기 ( 이러면 null 못생기지 )
        patchentity.patch(boardentity);


        //정상처리
        Boardentity editedentity = boardreposit.save(patchentity); // 원래는, boardentity 저장 (DTO 객체 다 담아놓음)
                    // => find 한 새로운 객체(patchentity) + 기존의 boardentity 합쳐놓은 걸로 저장
        return ResponseEntity.status(HttpStatus.OK).body(editedentity);

    }

    @DeleteMapping("/api/allboard/{id}")
    public ResponseEntity<Boardentity> delete(@PathVariable Long id){

        Boardentity tobedeleted = boardreposit.findById(id).orElse(null);

        if (tobedeleted == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}

        boardreposit.delete(tobedeleted);

            return ResponseEntity.status(HttpStatus.OK).body(null);
    }






}
