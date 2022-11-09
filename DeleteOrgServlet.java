package com.demo;

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
    public void init() {//servlet初始化时执行，完成了两步工作加载驱动和建立连接
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//mysql驱动名称
        String dburl = "jdbc:sqlserver://localhost:1433; DatabaseName=daka";
        //jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
        String username = "sa";
        String password = "sql2012";
        try{
            Class.forName(driver); // （1）加载驱动程序
            // （2）创建连接对象
            dbconn = DriverManager.getConnection(
                    dburl,username,password);
            System.out.println("Connection Successful!");  //如果连接成功 控制台输出
        }catch(ClassNotFoundException e1){
            System.out.println(e1);
            getServletContext().log("驱动程序类找不到！");
        }catch(SQLException e2){
            System.out.println(e2);
        }
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
                response.sendRedirect("/daka/error.jsp");
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

