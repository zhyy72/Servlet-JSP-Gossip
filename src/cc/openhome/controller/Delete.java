package cc.openhome.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
		urlPatterns= {"/delete.do"},
		initParams= {
				@WebInitParam(name = "MEMBER_VIEW", value = "member.view"),
				@WebInitParam(name = "LOGIN_VIEW", value = "index.html")
		}
)
public class Delete extends HttpServlet {
//    private final String USERS = "F:/eclipse/workspace/Gossip/users";
    private String LOGIN_VIEW;
    private String MEMBER_VIEW;
    
    @Override
    public void init() throws ServletException{
    	MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }
    
	protected void doGet(HttpServletRequest request, 
	                     HttpServletResponse response) 
	                         throws ServletException, IOException {
        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_VIEW);
            return;
        }
        
        //删除用户文件夹中对应的文件
        String username = (String) request.getSession().getAttribute("login");
        String message = request.getParameter("message"); 
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		userService.deleteMessage(username, message);
//		request.getSession().setAttribute("login", username);
        response.sendRedirect(MEMBER_VIEW);
	}
}
