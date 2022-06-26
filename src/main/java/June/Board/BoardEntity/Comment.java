package June.Board.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "Comment")
public class Comment{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "board_id")
    @ManyToOne
    private Boardentity boardentity;

    @Column
    private String nickname;
    @Column
    private String body;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", boardentity=" + boardentity +
                ", nickname='" + nickname + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
