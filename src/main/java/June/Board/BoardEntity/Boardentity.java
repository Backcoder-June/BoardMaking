package June.Board.BoardEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@ToString
@Entity
public class Boardentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String contents;

    public Boardentity(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Boardentity(){}

    public void patch(Boardentity boardentity) {

        //있는 경우에만
        if (boardentity.title != null){
            this.title = boardentity.title;}
        if (boardentity.contents != null) {
            this.contents = boardentity.contents;}

    }



}