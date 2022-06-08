/*
package June.Board;

import June.Board.BoardREposit.Boardreposit;
import June.Board.BoardREposit.interfacereposit;
import June.Board.BoardService.Boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    private final interfacereposit interfaceREposit;

    @Autowired
    public SpringConfig(interfacereposit interfaceREposit) {
        this.interfaceREposit = interfaceREposit;
    }

    @Bean
    public Boardservice boardservice(){
        return new Boardservice(interfaceREposit);}


}
*/
