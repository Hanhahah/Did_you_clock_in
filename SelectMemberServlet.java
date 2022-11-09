package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectMemberServlet")
public class SelectMemberServlet extends HttpServlet {
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
        String username = request.getParameter("username");//2
        ArrayList<User> user_list = null;
        user_list = new ArrayList<User>();

        try{
                String sql = "SELECT * FROM users WHERE username=?";//查询指定
                PreparedStatement pstmt = con.prepareStatement(sql);//
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    User u = new User();
                    u.setTel(rs.getString(1));
                    u.setUsername(rs.getString(2));
                    user_list.add(u);
                }

            if(!user_list.isEmpty()){
                request.getSession().setAttribute("user_list",user_list);
                response.sendRedirect("/daka/member.jsp");
            }
            else{
                request.setAttribute("msg2","该用户不在组织中");
                response.sendRedirect("/daka/member.jsp");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        ArrayList<User> user_list = null;
        user_list = new ArrayList<User>();
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int onumber = Integer.parseInt(request.getParameter("onumber"));
        request.getSession().setAttribute("onumber",onumber);
        onumber = (int) request.getSession().getAttribute("onumber");
        try{
                String sql="SELECT telephone FROM UO WHERE Onumber=?";//查询成员tel
                PreparedStatement pstmt = con.prepareStatement(sql);//
                pstmt.setInt(1,onumber);
                ResultSet rs = pstmt.executeQuery();
                String tel;
                while(rs.next()) {
                    tel = rs.getString(1);
                    String sql1 = "SELECT * FROM users WHERE telephone=?";//查询组织成员
                    PreparedStatement pstmt1 = con.prepareStatement(sql1);
                    pstmt1.setString(1, tel);
                    ResultSet rs1 = pstmt1.executeQuery();
                	System.out.print(rs1);
                    while (rs1.next()) {
                        User u = new User();
                        u.setTel(rs1.getString(1));
                        u.setUsername(rs1.getString(2));
                        user_list.add(u);
                    }
                }
          
            if(!user_list.isEmpty()){
                request.getSession().setAttribute("user_list",user_list);
            }else {
                request.getSession().setAttribute("msg3", "暂无创建的组织！");
            }
            response.sendRedirect("/Testing/member.jsp");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
