package com.demo;

import com.util.OpenConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
 * Servlet implementation class JoinServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn=null;
    int invite=0;

    @Override
    public void init() {


        OpenConnection open = new OpenConnection();
        conn = open.getConnection();

    }
	@Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
		Statement sql;
		ResultSet rs1;
		try {
			sql=conn.createStatement();
			int x=0;
            DateFormat sfm = new SimpleDateFormat("hh:mm:ss");
            while(x==0) {
            Random random=new Random();
            invite=(int)(Math.random()*999999-100000)+100000;
            rs1=sql.executeQuery("SELECT Onumber FROM organize WHERE invite="+invite); //查询 student 表
            if(!rs1.next()){
                x=1;
                request.getSession().setAttribute("invite",invite);
                System.out.println(invite+"邀请码1");
            }
            }
            response.sendRedirect("create.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        Statement sql;
        ResultSet rs;
        ResultSet rs1;
        int num=0;
        try {
            sql=conn.createStatement();
            System.out.println(invite+"邀请码");
            int invite1=(int)request.getSession().getAttribute("invite");
            String oname=request.getParameter("oname");           
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");			 
			 Date time = sdf.parse(request.getParameter("stime"));
			 System.out.println("4");
			 System.out.println("2");
            Time ctime = new Time(time.getTime());
            Date time2=sdf.parse(request.getParameter("atime"));
            System.out.println(time2);
            Time atime = new Time(time2.getTime());
            String telephone= (String) request.getSession().getAttribute("telephone");
            String sql1="insert into organize(Oname,telephone,invite,report_time,remind_time) values(?,?,?,?,?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql1);
	        pstmt.setString(1,oname);
	        pstmt.setString(2,telephone);
	        pstmt.setInt(3,invite1);
	        pstmt.setTime(4,ctime);
	        pstmt.setTime(5,atime);
	        pstmt.executeUpdate();
            
            rs=sql.executeQuery("SELECT Onumber FROM organize WHERE invite="+invite);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                num=rs.getInt(1);
                System.out.println(num+"看这里");
            }
            int cr1=sql.executeUpdate("insert into UO(telephone,Onumber) values(16680802859,"+num+");");
            response.sendRedirect("create.jsp");
        }
        catch(SQLException e) {
            System.out.println(e);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	

}
