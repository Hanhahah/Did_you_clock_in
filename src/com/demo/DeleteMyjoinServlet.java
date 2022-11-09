package com.demo;

import com.model.Clockin;
import com.util.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@WebServlet("/delete-join")
public class DeleteMyjoinServlet extends HttpServlet {
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
                int idonu=Integer.parseInt(request.getParameter("idonu"));
                String sql="DELETE FROM UO WHERE telephone =? and Onumber=?";
                pstmt = dbconn.prepareStatement(sql);
                pstmt.setString(1, telphone);
                pstmt.setInt(2, idonu);
                int n  = pstmt.executeUpdate();
                response.sendRedirect("/test1_war_exploded/MyjoinServlet");


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
        request.getSession().setAttribute("telephone",newtel);
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


