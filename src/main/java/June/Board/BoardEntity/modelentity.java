package June.Board.BoardEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter @Setter
public class modelentity {


    private String username = "June";

    private String marketer = "Tom";

    private LocalDate nowdate = LocalDate.now();




}
