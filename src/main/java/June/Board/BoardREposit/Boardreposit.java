package June.Board.BoardREposit;

import June.Board.BoardEntity.Boardentity;
import June.Board.BoardEntity.MarketerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Boardreposit extends CrudRepository<Boardentity, Long> {
    @Override
    ArrayList<Boardentity> findAll();

    static Map<Long, Boardentity> store = new HashMap<>();

    public static void clearman(){
        store.clear();
    }









}
