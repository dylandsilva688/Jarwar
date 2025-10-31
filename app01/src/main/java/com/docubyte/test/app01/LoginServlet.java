package com.docubyte.test.app01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");


        String status1 = "";
        String status2 = "";

        out.println("<html>");
        out.println("<body>");
        if(uname.equals("dog") && upwd.equals("dog")){
            status1 = "User Login Success";
            out.println("<h3 style='color: blue; text-align: center;'>User Login Status Page</h3>");
            out.println("<h1 style='color: green; text-align: center;'>"+status1+"</h1>");
            out.println("<br><br>");
            out.println("<h3 style='text-align: center'><a href='./menu.html'>|MENU FORM|</h3>");
        }else{
            status2 = "User Login Failure";
            out.println("<h3 style='color: blue; text-align: center;'>User Login Status Page</h3>");
            out.println("<h1 style='color: green; text-align: center;'>"+status2+"</h1>");
        }
        out.println("</body></html>");




    }
}

