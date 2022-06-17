package June.Board.BoardController;

import June.Board.BoardEntity.Boardentity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class boardform {

    private Long id;

    private String title;
    private String contents;


    /*
    public Boardentity toEntity(){
        return new Boardentity(id, title, contents);
    }*/

}
