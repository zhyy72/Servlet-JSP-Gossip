package cc.openhome.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/success.view")
public class Success extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//������Ӧ����
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset= UTF-8\">");
		out.println("<title>������Ա�ɹ�</title>");
		out.println("</head>");
		out.println("<body>");		
		out.println("<h1>��Ա " + request.getParameter("username") + " ע��ɹ�</h1>");
		out.println("<a href='index.html'>���ص�¼��ҳ</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
