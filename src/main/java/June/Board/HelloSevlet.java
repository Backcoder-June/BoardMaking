package June.Board;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloSevlet", urlPatterns = "/home")
public class HelloSevlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response){
        //API 로직 작성
    }
}
