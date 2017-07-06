import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
  
public class mama extends HttpServlet {  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
        response.setContentType("text/plain; charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        PrintWriter printWriter = response.getWriter();  
        String me = "[{\"Url\":\"http://114.115.142.42/img/1/e5zmpxhkk94pnzxxv4yfirgr.jpg\",\"picId\":\"1\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/v7jfq6qj6owdxjrmic47vir7.jpg\",\"picId\":\"2\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/r6ha9hqtpi8s21rvgbnm1gyz.jpg\",\"picId\":\"3\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/yl9migi2g1bu7s3f03twjy7u.jpg\",\"picId\":\"4\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/iwhevxtxtju5tzudbkp07wl1.jpg\",\"picId\":\"5\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/08zv736rfilazzuj9zwsogd9.jpg\",\"picId\":\"6\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/8qtuc99bei5f1e18zbqianj0.jpg\",\"picId\":\"7\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自动柜员机\",\"desContent2\":\"ATM\",\"desContent3\":\"自助取款机\"},{\"Url\":\"http://114.115.142.42/img/1/855x91euk7kyqnmnxtz5v3lm.jpg\",\"picId\":\"8\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动存取款一体机\"},{\"Url\":\"http://114.115.142.42/img/1/5i1kvc3p59pqnk1uktub71yo.jpg\",\"picId\":\"9\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM机\",\"desContent3\":\"自动柜员机\"},{\"Url\":\"http://114.115.142.42/img/1/t7cj4ek86m42fvtemmv5yz5v.jpg\",\"picId\":\"10\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"Automatic Teller Machine\",\"desContent2\":\"ATM\",\"desContent3\":\"自动取款机\"},{\"Url\":\"http://114.115.142.42/img/1/2w0x66hvj9ssvsz8qp0oi8tb.jpg\",\"picId\":\"11\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动存取款一体机\"},{\"Url\":\"http://114.115.142.42/img/1/kqwnye3rpy372m6xwgq8itsk.jpg\",\"picId\":\"12\",\"isFinish\":\"1\",\"desNum\":\"3\",\"desContent1\":\"自助取款机\",\"desContent2\":\"ATM\",\"desContent3\":\"自动柜员机\"}]";
        printWriter.print(me);
        printWriter.flush();  
        printWriter.close();  
    }  
} 

