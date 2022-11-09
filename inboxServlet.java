package com.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.model.inbox;

/**
 * Servlet implementation class inboxServlet
 */
@WebServlet("/inboxServlet")
public class inboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    DataSource dataSource;
    @Override
    public void init() {
        try{
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/webstoreDS");
            dbconn=dataSource.getConnection();
            System.out.println("Success");
        }catch(NamingException ne){
            System.out.println("lose"+ne);
        }catch(SQLException se){
            System.out.println("lose"+se);
        }
    }
    public inboxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<inbox> inboxlist = null; 
	     inboxlist = new ArrayList<inbox>();
	     try{
        String sql="SELECT * FROM inbox";
        PreparedStatement pstmt = dbconn.prepareStatement(sql);
        ResultSet result = pstmt.executeQuery();
        while(result.next()){
           inbox inbox = new inbox();
           inbox.setGdate(result.getDate("gdate"));
           inbox.setOnumber(result.getInt("onumber"));
           inbox.setReport(result.getString("report"));
           inboxlist.add(inbox);
           System.out.println("0");
        }
        System.out.println(inboxlist);
        if(!inboxlist.isEmpty()){
   	       request.getSession().setAttribute("inboxlist",inboxlist);
   	    response.sendRedirect("mail.jsp");
        }else{
           response.sendRedirect("/error.jsp");
        }
      }catch(SQLException e){
 	      e.printStackTrace();
      }
  }
  public void destroy1(){
	      try {
	         dbconn.close();//5����ʱ�ر�����
	      }catch(Exception e){
		     e.printStackTrace();
     }

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ArrayList<inbox> inboxlist = null; 
		     inboxlist = new ArrayList<inbox>();
		     try{
	         String sql="SELECT * FROM inbox";
	         PreparedStatement pstmt = dbconn.prepareStatement(sql);
	         ResultSet result = pstmt.executeQuery();
	         while(result.next()){
	            inbox inbox = new inbox();
	            inbox.setGdate(result.getDate("gdate"));
	            inbox.setOnumber(result.getInt("onumber"));
	            inbox.setReport(result.getString("report"));
	            inboxlist.add(inbox);
	            System.out.println("0");
	         }
	         System.out.println(inboxlist);
	         if(!inboxlist.isEmpty()){
	    	       request.getSession().setAttribute("inboxlist",inboxlist);
	            response.sendRedirect("/mail.jsp");
	         }else{
	            response.sendRedirect("/error.jsp");
	         }
	       }catch(SQLException e){
	  	      e.printStackTrace();
	       }
	   }
	   public void destroy(){
		      try {
		         dbconn.close();//5����ʱ�ر�����
		      }catch(Exception e){
			     e.printStackTrace();
	      }

}

}
