package murach.controller;

import murach.user.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/emailList"})
public class EmailListServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
       
        String url = "/index.html";
        
        String action = request.getParameter("action");
        if (action == null){
            action = "join";
        }
         
        if (action.equals("join")){
            url = "/index.html";
        }
        else if (action.equals("add")){
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            byte[] bytes = firstName.getBytes(StandardCharsets.ISO_8859_1);
            firstName = new String(bytes, StandardCharsets.UTF_8);

            String lastName = request.getParameter("lastName");
            bytes = lastName.getBytes(StandardCharsets.ISO_8859_1);
            lastName = new String(bytes, StandardCharsets.UTF_8);
            String email = request.getParameter("email");
            
            //store data in User object add save User object in database
            User user = new User(firstName, lastName, email);
//            UserDB.insert(user);
            
            //set User object in request object and set URL
            request.setAttribute("user", user);
            url = "/thanks.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request, response);
    }
}
