package com.demo;

import com.model.Clockin;
import com.util.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@SuppressWarnings("AlibabaClassMustHaveAuthor")
@WebServlet("/clockServlet")

public class ClockServlet extends HttpServlet {
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
        doGet(request,response);
        String[] onumber= request.getParameterValues("Onumber");
        int[] onu = new int[onumber.length];
        int i=0;
        for (String c:onumber){
            onu[i]=Integer.parseInt(c);
        }
        String telphone= (String) request.getSession().getAttribute("telephone");
        String location= request.getParameter("location");
        location=new String(location.getBytes("ISO-8859-1"),"utf-8");
        String helthcode= request.getParameter("helthcode");
        helthcode=new String(helthcode.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(helthcode);
        String tripcode= request.getParameter("tripcode");
        tripcode=new String(tripcode.getBytes("ISO-8859-1"),"utf-8");
        String natresult= request.getParameter("NATresult");
        natresult=new String(natresult.getBytes("ISO-8859-1"),"utf-8");
        LocalTime time= LocalTime.now();
        // get the current time
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(time.format(formatter1));
        LocalDateTime dateTime = LocalDateTime.now();
        // get the current date and time
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dateTime.format(formatter2));
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        DateFormat df = new SimpleDateFormat("HH:mm:ss");

        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PreparedStatement pstmt;
        ArrayList<Clockin> list = null;
        list = new ArrayList<Clockin>();
        try{
            for (int o:onu) {
                System.out.println(o);
                String sql1="SELECT * FROM organize where Onumber=?";
                pstmt=dbconn.prepareStatement(sql1);
                pstmt.setInt(1,o);
                ResultSet rs = pstmt.executeQuery();
               if (rs.next()){
                   System.out.println(o);
                    java.sql.Date report=rs.getDate(5);
                   java.util.Date getdate =new java.util.Date(report.getTime());
                       Date date1 = df.parse(time.format(formatter1));
                       if (date1.getTime() <= getdate.getTime()) {

                          java.sql.Date time1 =new java.sql.Date((df1.parse(dateTime.format(formatter2))).getTime());

                           String sql = "insert into clockin values(?,?,?,?,?,?,?)";
                           pstmt = dbconn.prepareStatement(sql);

                           pstmt.setInt(1, o);
                           pstmt.setString(2, telphone);
                           pstmt.setDate(3, time1);
                           pstmt.setString(4, location);
                           pstmt.setString(5, helthcode);
                           pstmt.setString(6, tripcode);
                           pstmt.setString(7, natresult);
                           int num = pstmt.executeUpdate();
                           if (num != 0) {
                               request.getRequestDispatcher("index.jsp").forward(request, response);
                               System.out.println("成功了");
                           } else {
                               System.out.println("失败了");
                           }
                       }

                }


            }

        }catch(SQLException | ParseException e){
            e.printStackTrace();
            System.out.println("失败了!!!!!");
        }System.out.printf(telphone);
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
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


