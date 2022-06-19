package June.Board.BoardService;

import June.Board.BoardController.boardform;
import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Boardservice {

    private final Boardreposit boardreposit;
    @Autowired
    public Boardservice(Boardreposit boardreposit) {
        this.boardreposit = boardreposit;
    }

    public List<Boardentity> boardindex(){
        return boardreposit.findAll();
    }

    public Boardentity show(Long id){

        return boardreposit.findById(id).orElse(null);
    }

    public Boardentity create(boardform dto){

        Boardentity boardentity = new Boardentity();
        boardentity.setId(dto.getId());
        boardentity.setTitle(dto.getTitle());
        boardentity.setContents(dto.getContents());

        if (boardentity.getId() != null){
            return null;}

        return boardreposit.save(boardentity);
    }




}