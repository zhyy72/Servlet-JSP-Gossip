package cc.openhome.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.*;

@WebServlet(
		urlPatterns= {"/login.do"},
		initParams= {
				@WebInitParam(name = "SUCCESS_VIEW", value = "member.view"),
				@WebInitParam(name = "ERROR_VIEW", value = "index.html")
		}
		)
public class Login extends HttpServlet {
//	private final String USERS = "F:/eclipse/workspace/Gossip/users";
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	
	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String page = ERROR_VIEW;
		// 检查用户名称与密码是否符合，若是则转发会员页面，若不是则重定向回首页
		String Code = (String) request.getSession().getAttribute("Code");
//		System.out.println(Code);
		if (Code.equals(code)) {
//			System.out.println(Code);
			UserService userService = 
					(UserService) getServletContext().getAttribute("userService");
//			System.out.println(username);
//			System.out.println(password);
//			if(userService.getUsers() == null) {
//				System.out.println("null");
//			}
//			System.out.println(userService.getUsers());
			if (userService.checkLogin(username, password)) {
				// request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
				request.getSession().setAttribute("login", username);
				page = SUCCESS_VIEW;
			}
		}

		response.sendRedirect(page);

	}

//	//检查登陆用户名称与密码
//	private boolean checkLogin(String username, String password) throws IOException {
//		// TODO Auto-generated method stub
//		if (username != null && password != null) {
//			// 读取用户资料夹中的profile文件器
//			for (String file : new File(USERS).list()) {
//				if (file.equals(username)) {
//					BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
//					String passwd = reader.readLine().split("\t")[1];
//					if (passwd.equals(password)) {
//						reader.close();
//						return true;
//					}
//					reader.close();
//				}
//			}
//		}
//		return false;
//	}
}
