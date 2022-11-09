package com.demo;

import com.model.Clockin;
import com.util.OpenConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 * @author hu
 * @date 2022/11/9
 */
@WebServlet("/detailServlet")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection dbconn = null;
    @Override
    public void init() {


        OpenConnection open = new OpenConnection();
        dbconn = open.getConnection();

    }
    @Override
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String telphone= (String) request.getSession().getAttribute("telephone");
        LocalDateTime dateTime = LocalDateTime.now();
        // get the current date and time
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dateTime.format(formatter2));
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pstmt;
        ArrayList<Clockin> list = null;
        list = new ArrayList<Clockin>();
        try{
                String sql1="SELECT * FROM clockin where telephone=?";
                pstmt=dbconn.prepareStatement(sql1);
                pstmt.setString(1,telphone);
                ResultSet rs = pstmt.executeQuery();
                Clockin cl=new Clockin();
                List<Integer> conu=new ArrayList<Integer>();
                while (rs.next()){
                    java.sql.Date report=rs.getDate(3);
                    java.util.Date date =new java.util.Date(report.getTime());
                   Date date1 = df.parse(df.format(date));
                    System.out.println("hhhh22"+date);
                    Date date2=df.parse(dateTime.format(formatter2));
                    if (date.compareTo(date2)==0) {
                        System.out.println("hhhh33"+date2);
                        cl.setCdate(date);
                        cl.setOnumber(rs.getInt(1));
                        cl.setTelephone(telphone);
                        cl.setLocation(rs.getString(4));
                        cl.setHelthcode(rs.getString(5));
                        cl.setTripcode(rs.getString(6));
                        cl.setNatresult(rs.getString(7));
                        conu.add(rs.getInt(1));
                    }
            }
                request.getSession().setAttribute("cl", cl);
            request.getSession().setAttribute("conu", conu);
            response.sendRedirect("/test1_war_exploded/detail.jsp");
        }catch(SQLException | ParseException e){
            e.printStackTrace();
            System.out.println("失败了!!!!!");
        }System.out.printf(telphone);
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
    }



    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException,IOException{
        response.setContentType("text/html);charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String telphone= (String) request.getSession().getAttribute("telephone");
        LocalDateTime dateTime = LocalDateTime.now();
        // get the current date and time
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(dateTime.format(formatter2));
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement pstmt;
        ArrayList<Clockin> list = null;
        list = new ArrayList<Clockin>();
        try{
            String sql1="SELECT * FROM clockin where telephone=?";
            pstmt=dbconn.prepareStatement(sql1);
            pstmt.setString(1,telphone);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                java.sql.Date report=rs.getDate(3);
                java.util.Date date =new java.util.Date(report.getTime());
                Date date1 = df.parse(df.format(date));
                System.out.println("hhhh22"+date);
                Date date2=df.parse(request.getParameter("lookdate"));
                Clockin cl=new Clockin();
                List<Integer> conu=new ArrayList<Integer>();
                if (date.compareTo(date2)==0) {
                    System.out.println("hhhh33"+date2);
                    cl.setCdate(date);
                    cl.setOnumber(rs.getInt(1));
                    cl.setTelephone(telphone);
                    cl.setLocation(rs.getString(4));
                    cl.setHelthcode(rs.getString(5));
                    cl.setTripcode(rs.getString(6));
                    cl.setNatresult(rs.getString(7));
                    conu.add(rs.getInt(1));
                }

                request.getSession().setAttribute("cl", cl);
                request.getSession().setAttribute("conu", conu);


            }
            response.sendRedirect("/test1_war_exploded/detail.jsp");
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


