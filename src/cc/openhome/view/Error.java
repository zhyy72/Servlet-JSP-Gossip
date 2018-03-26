package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error.view")
public class Error extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//设置响应密码
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset= UTF-8\">");
		out.println("<title>新增会员失败</title>");
		out.println("</head>");
		out.println("<body>");		
		out.println("<h1>新增会员失败</h1>");
		out.println("<ul style='color: rgb(255, 0, 0);'>");
		
		//取得请求属性
		List<String> errors = (List<String>)request.getAttribute("errors");
		//显示错误信息
		for(String error: errors) {
			out.println("<li>" + error + "</li>");
		}
		out.println("</ul>");
		out.println("<a href='register.html'>返回注册页面</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
