package June.Board.BoardService;

import June.Board.BoardController.boardform;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Boardservice {

    private final Boardreposit boardreposit;
    @Autowired
    public Boardservice(Boardreposit boardreposit) {
        this.boardreposit = boardreposit;
    }

    //목록 가져오기
    public List<Boardentity> boardindex(){
        return boardreposit.findAll();
    }


    //해당글 가져오기
    public Boardentity show(Long id){

        return boardreposit.findById(id).orElse(null);
    }

    //글 작성
    public Boardentity create(boardform dto){

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        if (boardentity.getId() != null){
            return null;}

        return boardreposit.save(boardentity);
    }


    //글 수정
    public Boardentity edit(Long id, boardform dto) {

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        Boardentity target = boardreposit.findById(id).orElse(null);

        if (target==null || id!=boardentity.getId())
        {return null;}

        target.patch(boardentity);

        Boardentity edited = boardreposit.save(target);

        return edited;
    }


    //글 삭제
    public Boardentity delete(Long id){

        Boardentity deletetarget = boardreposit.findById(id).orElse(null);

        if (deletetarget == null){
            return null;
        }

        boardreposit.delete(deletetarget);

        return deletetarget;

    }
/*


    public List<Boardentity> creating(List<boardform> dtos) {
        // dtos 를 boardentity 에 담자

        Boardentity boardentity = new Boardentity();
        List<Boardentity> boardlist = dtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
*/


    // 트랜잭션과 롤백 실험용
    @Transactional
    public List<Boardentity> creating(List<boardform> dtos) {
        List<Boardentity> boardList = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            boardform dto = dtos.get(i);

            Boardentity boardentity = new Boardentity();
            boardentity.setId(dto.getId());
            boardentity.setTitle(dto.getTitle());
            boardentity.setContents(dto.getContents());

            boardList.add(boardentity);   }

        // boardentity DB에 저장
        boardList.stream().forEach(boardentitiy->boardreposit.save(boardentitiy));

        // 강제 예외 발생
        boardreposit.findById(-1L).orElseThrow(
                () -> new IllegalStateException("강제 예외 발생!")
        );


        // 결과값 반환
        return boardList;
    }




    }





