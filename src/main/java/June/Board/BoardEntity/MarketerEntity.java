package June.Board.BoardEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class MarketerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String counsel_category;
    @Column
    private String counsel_content;
    @Column
    private String counsel_unusual;



}
