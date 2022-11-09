package com.demo;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateDealServlet
 */
@WebServlet("/UpdateOrgServlet")
public class UpdateOrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String setname = request.getParameter("setname");
		Date time1 = (Date) sdf.parse(request.getParameter("report"));
        int onumber = (int) request.getSession().getAttribute("onumber");
        long lg1 = time1.getTime(); 
        java.sql.Date report = new java.sql.Date(time1.getTime());
		java.util.Date time2 = sdf.parse(request.getParameter("remind"));
        long lg2 = time2.getTime();
        java.sql.Date remind = new java.sql.Date(time1.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		 
		 Date time1 = (Date) sdf.parse(request.getParameter("report"));
		 long lg1 = time1.getTime(); 
        java.sql.Date report = new java.sql.Date(time1.getTime());
      
	       Date time2=(Date) sdf.parse(request.getParameter("remind"));
	       long lg2 = time2.getTime(); 
	       java.sql.Date remind = new java.sql.Date(time2.getTime());*/
			String setname = request.getParameter("setname");
			int onumber = Integer.parseInt(request.getSession().getAttribute("onumber").toString());
			request.setAttribute("onumber", onumber);
	        
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");			 
		       java.util.Date time =  sdf.parse(request.getParameter("report"));
	           java.sql.Time report = new java.sql.Time(time.getTime());
	           java.util.Date time2=(Date)sdf.parse(request.getParameter("remind"));
	           java.sql.Time remind = new java.sql.Time(time2.getTime());
		String sql="UPDATE organize SET Oname = ? , report_time=?, remind_time=? WHERE onumber=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1,setname);
        pstmt.setTime(2, report);
        pstmt.setTime(3, remind);
        pstmt.setInt(4, onumber);
        System.out.print(setname+"皇家空军");
        System.out.print(report+"皇家空军");
        System.out.print(remind+"皇家空军");
        System.out.print(onumber+"皇家空军");

        int n  = pstmt.executeUpdate();
          if(n!=0){
              ServletContext sc = getServletContext();

              RequestDispatcher rd = null;

              rd = sc.getRequestDispatcher("/setteam.jsp"); //定向的页面

              rd.forward(request, response);
	        }else{
	           response.sendRedirect("/daka/error.jsp");
	        }	
      }catch(SQLException e){
  	      e.printStackTrace();
      } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	

}
