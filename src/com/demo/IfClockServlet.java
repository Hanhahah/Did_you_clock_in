package com.demo;

        import com.model.Organization;
        import com.util.OpenConnection;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.sql.DataSource;
        import java.io.IOException;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@WebServlet("/if_clockServlet")
public class IfClockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    DataSource dataSource;
    @Override
    public void init() {


        OpenConnection open = new OpenConnection();
        dbconn = open.getConnection();

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String telphone= (String) request.getSession().getAttribute("telephone");
        System.out.println(telphone);

        try{
            String sql="SELECT * FROM UO where telephone=?";
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1,telphone);
            ResultSet rst = pstmt.executeQuery();

            List<Organization> onulist=new ArrayList<>();
            while(rst.next()){
                Organization onu=new Organization();
                onu.setOnumber(rst.getInt(2));

                sql="SELECT * FROM organize where Onumber=?";
                pstmt = dbconn.prepareStatement(sql);
                pstmt.setInt(1,onu.getOnumber());
                ResultSet rst2 = pstmt.executeQuery();
                if(rst2.next()){
                onu.setOname(rst.getString(2));
                System.out.println(rst.getString(2));}
                onulist.add(onu);
            }
            request.getSession().setAttribute("onulist", onulist);
            response.sendRedirect("/test1_war_exploded/index.jsp");
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);

    }


    @Override
    public void destroy(){
        try {
            dbconn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


