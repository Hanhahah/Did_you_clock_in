package com.demo;

import com.model.Organize;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static javax.swing.UIManager.getInt;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectStasServlet")
public class SelectStasServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() {//servlet初始化时执行，完成了两步工作加载驱动和建立连接
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//mysql驱动名称
        String dburl = "jdbc:sqlserver://localhost:1433; DatabaseName=daka";
        String username = "sa";
        String password = "sql2012";
        try{
            Class.forName(driver); // （1）加载驱动程序
            // （2）创建连接对象
            con = DriverManager.getConnection(dburl,username,password);
            System.out.println("Connection Successful!");  //如果连接成功 控制台输出
        }catch(ClassNotFoundException e1){
            System.out.println(e1);
            getServletContext().log("驱动程序类找不到！");
        }catch(SQLException e2){
            System.out.println(e2);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        CallableStatement cstmt = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(request.getParameter("searchdate"));
            int onumber = Integer.parseInt(request.getSession().getAttribute("onumber").toString());
            long lg = date.getTime();
            //onumber = (int) request.getSession().getAttribute("onumber");
            request.setAttribute("onumber", onumber);
            
            cstmt=con.prepareCall("{call dbo.pro(?,?,?,?,?,?,?)}");
            cstmt.setInt(1,onumber);
            cstmt.setDate(2, new java.sql.Date(lg) );
            cstmt.registerOutParameter(3,Types.INTEGER);
            cstmt.registerOutParameter(4,Types.INTEGER);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.registerOutParameter(7, Types.INTEGER);
            cstmt.execute();

            int[] stas = new int[5];
            stas[0] = cstmt.getInt(3);
            stas[1] = cstmt.getInt(4);
            stas[2] = cstmt.getInt(5);
            stas[3] = cstmt.getInt(6);
            stas[4] = cstmt.getInt(7);

            request.setAttribute("stas", stas);
            //response.sendRedirect("statistics.jsp");
            ServletContext sc = getServletContext();

            RequestDispatcher rd = null;

            rd = sc.getRequestDispatcher("/statistics.jsp"); //定向的页面

            rd.forward(request, response);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request,response);

    }
}
