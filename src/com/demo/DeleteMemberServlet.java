package com.demo;
import com.util.OpenConnection;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


import javax.servlet.annotation.WebServlet;
@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet{
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
        String tel = request.getParameter("tel");
        int onumber = (int) request.getSession().getAttribute("onumber");
        try{
            String sql="DELETE FROM UO WHERE telephone =? and onumber=?";
            PreparedStatement pstmt = dbconn.prepareStatement(sql);
            pstmt.setString(1, tel);
            pstmt.setInt(2, onumber);
            int n  = pstmt.executeUpdate();
            if(n!=0){
                request.getRequestDispatcher("SelectMemberServlet").forward(request, response);
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

