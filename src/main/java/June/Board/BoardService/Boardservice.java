/*
package June.Board.BoardService;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardREposit.Boardreposit;
import June.Board.BoardREposit.interfacereposit;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Boardservice {


    private final interfacereposit interfaceReposit;

    public Boardservice(interfacereposit interfaceReposit) {
        this.interfaceReposit = interfaceReposit;
    }



    public Long saved(Boardentity boardentity){
        interfaceReposit.save(boardentity);
        return boardentity.getId();
    }



}
*/
