package Manager;
import Common.Label;
import Common.GetLabels;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import net.sf.json.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
public class MyTest{

    public static void main(String []args){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){
            System.out.println("数据库错误啦！！");
        }
        //String managerId = request.getParameter("managerId");
        try{
        String picSetId = "1";
            String url = "jdbc:mysql://localhost:3306/no3b?characterEncoding=utf8";
            String pasw = "ibm5100";
            String username = "root";
            Connection con = DriverManager.getConnection(url, username, pasw);
            Statement sql=con.createStatement();
            if(false){
            }
            else{

                ArrayList <String> picSetInfoList = new ArrayList <String>();
                ResultSet rs = sql.executeQuery("select * from picBase where belong="+picSetId);
                JSONArray jsonArray = new JSONArray(); //create JSONArray
                while(rs.next()){
                    System.out.println(rs.getString(1));
                    Map params = new HashMap();
                    //图片id 图片url tagNum tag1 tag2 tag3 tag4 tag5 question ansNum ans1 ans2 ans3   
                    //加入其他属性               
                    params.put("Url",rs.getString(3));
                    params.put("PicId",rs.getString(1));
                    params.put("isFinish",rs.getString(5));
                    //加入标签
                    GetLabels gl = new GetLabels();
                    ArrayList <Label> desList = gl.getLabels(rs.getString(1),"0");
                    ArrayList <Label> ansList = gl.getLabels(rs.getString(2),"1");
                    int desNum = 3;
                    //int ansNum = 5;
                    if(desList.size()<desNum) desNum = desList.size();
                    //if(ansList.length()<ansNum) ansNum = ansList.length();
                    params.put("desNum",desNum);
                    //params.put("ansNum",ansNum);
                    System.out.println(desNum);
                    for(int i=1;i<=desNum;i++){
                        String desContent = "desContent"+String.valueOf(i);
                        params.put(desContent,(desList.get(i)).getContent());
                        System.out.println((desList.get(i)).getContent());
                    }
                    JSONObject jsonObject=JSONObject.fromObject(params);
                    jsonArray.add(jsonObject);
                }
                System.out.println(jsonArray.toString());
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
                                                                                                    1,1           Top

