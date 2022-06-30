package June.Board.BoardEntity;

import June.Board.BoardController.CommentDto;
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

    public static Comment createMent(CommentDto dto, Boardentity fBi) {

        //예외 발생
        if(dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! id 가 없어야 합니다.");
        }

        if(dto.getId() != fBi.getId()){
            throw new IllegalArgumentException("댓글생성 실패! id 가 입력이랑 url이랑 다릅니다");
        }

        //정상 - 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                fBi,
                dto.getNickname(),
                dto.getBody()
        );


    }

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
