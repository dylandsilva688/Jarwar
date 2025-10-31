package com.docubyte.test.app01;

import com.docubyte.test.app01.beans.User;
import com.docubyte.test.app01.factory.ConnectionFactory;
import com.docubyte.test.app01.factory.UserDaoFactory;
import com.docubyte.test.app01.factory.UserServiceFactory;
import com.docubyte.test.app01.service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;




@WebServlet(value = "*.do", loadOnStartup = 1)
public class ControllerServlet extends HttpServlet {
    public void init() throws ServletException {
        ConnectionFactory.getConnection();
        UserDaoFactory.getUserDao();
        UserServiceFactory.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int id ;
        String name = "";
        String username = "";
        String password = "";
        String address = "";
        String mobileno = "";
        String emailid = "";
        String isactive = "" ;
        User user = null;
        String status = "";
        RequestDispatcher requestDispatcher = null;
        UserService userService = UserServiceFactory.getUserService();
        String requestURI = request.getRequestURI();

        if(requestURI.endsWith("add.do")) {
            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            username = request.getParameter("username");
            password = request.getParameter("password");
            address = request.getParameter("address");
            mobileno = request.getParameter("mobileno");
            emailid = request.getParameter("emailid");
            isactive = request.getParameter("is active");
            user = new User();
            user.setId(id);
            user.setName(name);
            user.setUsername(username);
            user.setPassword(password);
            user.setAddress(address);
            user.setMobileno(mobileno);
            user.setEmailid(emailid);
            user.setIsactive(isactive);
            status = userService.addUser(user);
            if (status.equalsIgnoreCase("success")) {
                requestDispatcher = request.getRequestDispatcher("success.html");
                requestDispatcher.forward(request, response);
            }
            if (status.equalsIgnoreCase("failure")) {
                requestDispatcher = request.getRequestDispatcher("failure.html");
                requestDispatcher.forward(request, response);
            }
            if (status.equalsIgnoreCase("existed")) {
                requestDispatcher = request.getRequestDispatcher("existed.html");
                requestDispatcher.forward(request, response);
            }
        }if(requestURI.endsWith("search.do")){
            id = Integer.parseInt(request.getParameter("id"));
            user = userService.searchUser(id);
            if(user == null){
                requestDispatcher = request.getRequestDispatcher("notexisted.html");
                requestDispatcher.forward(request, response);
            }else{
                out.println("<html>");
                out.println("<body>");
                out.println("<br><br><br>");
                out.println("<table style='margin-left: auto; margin-right: auto;' border='1'");
                out.println("<tr><td>Id</td><td>" + user.getId() + "</td></tr>");
                out.println("<tr><td>Name</td><td>" + user.getName() + "</td></tr>");
                out.println("<tr><td>Username</td><td>" + user.getUsername() + "</td></tr>");
                out.println("<tr><td>Password</td><td>" + user.getPassword() + "</td></tr>");
                out.println("<tr><td>Address</td><td>" + user.getAddress() + "</td></tr>");
                out.println("<tr><td>Mobileno</td><td>" + user.getMobileno() + "</td></tr>");
                out.println("<tr><td>Emailid</td><td>" + user.getEmailid() + "</td></tr>");
                out.println("<tr><td>Isactive</td><td>" + user.getIsactive() + "</td></tr>");
                out.println("<br><br>");
                out.println("<h3><a href='./addform.html'>ADD USER</a></h3>");
                out.println("<h3><a href='./searchform.html'>SEARCH USER</a></h3>");
                out.println("<h3><a href='./updateform.html'>UPDATE USER</a></h3>");
                out.println("<h3><a href='./deleteform.html'>DELETE USER</a></h3>");
                out.println("</table></body></html>");
            }

        }if(requestURI.endsWith("editform.do")) {
            id = Integer.parseInt(request.getParameter("id"));
            user = userService.searchUser(id);
            if (user == null) {
                requestDispatcher = request.getRequestDispatcher("notexisted.html");
                requestDispatcher.forward(request, response);
            } else {
                out.println("<html>");
                out.println("<body>");
                out.println("<br><br><br>");
                out.println("<form method='post' action='update.do'>");
                out.println("<table style='margin-left:auto; margin-right:auto;'>");
                out.println("<tr><td>Id : </td><td>" + user.getId() + "</td></tr>");
                out.println("<input type='hidden' name='id' value='" + user.getId() + "'>");
                out.println("<tr><td>Name</td><td><input type='text' name='name' value='" + user.getName() + "'></td></tr>");
                out.println("<tr><td>Username</td><td><input type='text' name='username' value='" + user.getUsername() + "'></td></tr>");
                out.println("<tr><td>Password</td><td><input type='text' name='password' value='" + user.getPassword() + "'></td></tr>");
                out.println("<tr><td>Address</td><td><input type='text' name='address' value='" + user.getAddress() + "'></td></tr>");
                out.println("<tr><td>Mobileno</td><td><input type='text' name='mobileno' value='" + user.getMobileno() + "'></td></tr>");
                out.println("<tr><td>Emailid</td><td><input type='text' name='emailid' value='" + user.getEmailid() + "'></td></tr>");
                out.println("<tr><td>Isactive</td><td><input type='text' name='isactive' value='" + user.getIsactive() + "'></td></tr>");
                out.println("<tr><td><input type='submit' value='UPDATE'></td></tr>");
                //out.println("<h3><a href='./addform.html'>ADD USER</a></h3>");
                // out.println("<h3><a href='./searchform.html'>SEARCH USER</a></h3>");
                //out.println("<h3><a href='./updateform.html'>UPDATE USER</a></h3>");
                //out.println("<h3><a href='./deleteform.html'>DELETE USER</a></h3>");
                out.println("</table></form></body></html>");
            }
        }if(requestURI.endsWith("update.do")){
            id = Integer.parseInt(request.getParameter("id"));
            name = request.getParameter("name");
            username = request.getParameter("username");
            password = request.getParameter("password");
            address = request.getParameter("address");
            mobileno = request.getParameter("mobileno");
            emailid = request.getParameter("emailid");
            isactive = request.getParameter("isactive");
            user = new User();
            user.setId(id);
            user.setName(name);
            user.setUsername(username);
            user.setPassword(password);
            user.setAddress(address);
            user.setMobileno(mobileno);
            user.setEmailid(emailid);
            user.setIsactive(isactive);
            status = userService.updateUser(user);
            if(status.equalsIgnoreCase("success")){
                requestDispatcher = request.getRequestDispatcher("success.html");
                requestDispatcher.forward(request, response);
            }
            if(status.equalsIgnoreCase("failure")){
                requestDispatcher = request.getRequestDispatcher("failure.html");
                requestDispatcher.forward(request, response);
            }
            out.println("<h3><a href='./addform.html'>ADD USER</a></h3>");
            out.println("<h3><a href='./searchform.html'>SEARCH USER</a></h3>");
            out.println("<h3><a href='./updateform.html'>UPDATE USER</a></h3>");
            out.println("<h3><a href='./deleteform.html'>DELETE USER</a></h3>");
        }if(requestURI.endsWith("delete.do")){
            id = Integer.parseInt(request.getParameter("id"));

            user = userService.searchUser(id);
            if(user == null){
                requestDispatcher = request.getRequestDispatcher("notexisted.html");
                requestDispatcher.forward(request, response);
            }else{
                //id = Integer.parseInt(userService.addUser(user));
                status = userService.deleteUser(id);
                if(status.equalsIgnoreCase("success")){
                    requestDispatcher = request.getRequestDispatcher("success.html");
                    requestDispatcher.forward(request, response);
                }
                if(status.equalsIgnoreCase("failure")){
                    requestDispatcher = request.getRequestDispatcher("failure.html");
                    requestDispatcher.forward(request, response);
                }
            }
            out.println("<h3><a href='./addform.html'>ADD USER</a></h3>");
            out.println("<h3><a href='./searchform.html'>SEARCH USER</a></h3>");
            out.println("<h3><a href='./updateform.html'>UPDATE USER</a></h3>");
            out.println("<h3><a href='./deleteform.html'>DELETE USER</a></h3>");
        }
        //@Override
        //public void destroy(){
            //ConnectionFactory.close();
        //}

    }
}




