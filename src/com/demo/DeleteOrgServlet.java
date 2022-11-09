package com.demo;

import com.util.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteOrgServlet")
public class DeleteOrgServlet extends HttpServlet{
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
        int onumber = Integer.parseInt(request.getParameter("onumber"));
        request.getSession().setAttribute("onumber",onumber);
        System.out.print(onumber);
        try{
            String sql="DELETE FROM organize WHERE Onumber =?";
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setInt(1, onumber);
            int n  = pstmt.executeUpdate();
            if(n!=0){
                request.getRequestDispatcher("SelectOrgServlet").forward(request, response);
            }else{
                response.sendRedirect("/test1_war_exploded/error.jsp");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
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

