import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn=null;
	public void init(){
	 try { 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 System.out.println("加载驱动成功！");
	 }
	 catch(Exception e){
	 }
	 try{
	 String uri= 
	 "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433;DatabaseName=daka";
	 String user="sa";
	 String password="sql2012";
	 conn=DriverManager.getConnection(uri,user,password);
	 System.out.println("连接数据库成功！");
	 }
	 catch(SQLException e){
	 System.out.println(e);
	 }
	}

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out=response.getWriter();
        Statement stmt;
        Statement stmt1;
        ResultSet rs;
        try {
            //sql=con.createStatement();
        	int num=0;
            String cno1=request.getParameter("invite");
            int inv=Integer.parseInt(cno1);
            System.out.println(cno1);
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();
            // 执行SQL语句
            rs = stmt.executeQuery("select Onumber from organize where invite="+cno1);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                num=rs.getInt(1);
                System.out.println(num+"看这里");
            }
            String telephone= (String) request.getSession().getAttribute("telephone");
            int tj=stmt1.executeUpdate("insert into UO values("+telephone+","+num+")");
            System.out.println("到这里了1");
            response.sendRedirect("join.jsp");
        }
        catch(SQLException e) {
            System.out.println(e);

        }
    }

}
