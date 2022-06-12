package June.Board.BoardREposit;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.MarketerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface Boardreposit extends CrudRepository<Boardentity, Long> {


}
