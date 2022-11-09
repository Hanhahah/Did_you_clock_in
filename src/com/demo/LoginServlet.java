package com.demo;

import com.util.OpenConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    DataSource dataSource;
   @Override
    public void init() {


            OpenConnection open = new OpenConnection();
            dbconn = open.getConnection();

    }
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        String telphone= request.getParameter("telephone");
        request.getSession().setAttribute("telephone", telphone);
        System.out.printf(telphone);
        request.getRequestDispatcher("/if_clockServlet").forward(request, response);
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


