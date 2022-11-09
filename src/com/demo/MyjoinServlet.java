package com.demo;

import com.model.Clockin;
import com.model.Organize;
import com.util.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@WebServlet("/MyjoinServlet")
public class MyjoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    @Override
    public void init() {


        OpenConnection open = new OpenConnection();
        dbconn = open.getConnection();

    }
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String telphone= (String) request.getSession().getAttribute("telephone");
        String name=null;
        PreparedStatement pstmt;

        try{
            String uri = request.getRequestURI();
                String sql1 = "SELECT * FROM UO where telephone=?";
                pstmt = dbconn.prepareStatement(sql1);
                pstmt.setString(1, telphone);
                ResultSet rs1 = pstmt.executeQuery();
                List<Organize> joinlist = new ArrayList<>();
                while (rs1.next()) {
                    Organize join = new Organize();
                    join.setOnumber(rs1.getInt(2));
                    sql1 = "SELECT * FROM organize where Onumber=?";
                    pstmt = dbconn.prepareStatement(sql1);
                    pstmt.setInt(1, join.getOnumber());
                    ResultSet rs2 = pstmt.executeQuery();
                    if(rs2.next())
                    {
                    join.setOname(rs2.getString(2));
                    }
                    joinlist.add(join);


                }
                request.getSession().setAttribute("joinlist", joinlist);
                response.sendRedirect("/test1_war_exploded/myjoin.jsp");



        }catch(SQLException  e){
            e.printStackTrace();
            System.out.println("失败了!!!!!");
        }System.out.printf(telphone);
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
    }



    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String telphone= (String) request.getSession().getAttribute("telephone");
        String newtel=request.getParameter("newtel");
        request.getSession().setAttribute("telphone",newtel);
        PreparedStatement pstmt;
        ArrayList<Clockin> list = null;
        list = new ArrayList<Clockin>();
        try{
            String sql1="update users set telephone=? where telephone=?";
            pstmt=dbconn.prepareStatement(sql1);
            pstmt.setString(1,newtel);
            pstmt.setString(2,telphone);


            int num = pstmt.executeUpdate();
            if (num != 0) {
                System.out.println("成功了");
            } else {
                System.out.println("失败了");
            }
            response.sendRedirect("/test1_war_exploded/user.jsp");


        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("失败了!!!!!");
        }System.out.printf(telphone);
        //request.getRequestDispatcher("/index.jsp").forward(request, response);


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


