package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Organize;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectOrgServlet")
public class SelectOrgServlet extends HttpServlet {
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
        String oname = request.getParameter("oname");//2

        try{
                String sql="SELECT * FROM Organize WHERE Oname=?";//查询指定组织
                PreparedStatement pstmt = con.prepareStatement(sql);//
                pstmt.setString(1,oname);
                ResultSet rs = pstmt.executeQuery();
                ArrayList<Organize> org_list = null;
                org_list = new ArrayList<Organize>();
                while(rs.next()){
                    Organize d = new Organize();
                    d.setOrgNum(rs.getInt(1));
                    d.setOrgName(rs.getString(2));
                    org_list.add(d);
                }

            if(!org_list.isEmpty()){
                request.getSession().setAttribute("org_list",org_list);
                response.sendRedirect("/daka/org-list.jsp");
            }
            else{
                request.setAttribute("msg2","该组织不存在");
                response.sendRedirect("/daka/org-list.jsp");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Statement st,st2;
        ArrayList<Organize> org_list = null;
        org_list = new ArrayList<Organize>();
        try{
            String sql="SELECT * FROM Organize";	//查询所有订单
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Organize d = new Organize();
                d.setOrgNum(rs.getInt(1));
                d.setOrgName(rs.getString(2));
                org_list.add(d);	//存放订单列表信息
            }
            if(!org_list.isEmpty()){
                request.getSession().setAttribute("org_list",org_list);
            }else {
                request.getSession().setAttribute("msg3", "暂无创建的组织！");
            }
            response.sendRedirect("/daka/org-list.jsp");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
