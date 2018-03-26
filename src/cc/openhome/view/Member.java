package cc.openhome.view;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
		urlPatterns= {"/member.view"},
		initParams= {
				@WebInitParam(name = "LOGIN_VIEW", value = "index.html"),
		}
)
public class Member extends HttpServlet {
//	private final String USERS = "F:/eclipse/workspace/Gossip/users";
	private String LOGIN_VIEW;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("login") == null) {
			response.sendRedirect(LOGIN_VIEW);
			return;
		}

		String username = (String) request.getSession().getAttribute("login");

		// ������Ӧ����
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset= UTF-8\">");
		out.println("<title>Gossip ΢��־</title>");
		out.println("</head>");
		out.println("<body>");

		out.println("<div class='leftPanel'>");
		out.println("<img src='images/caterpillar.jpg' alt='Gossip ΢��' /><br><br>");
		out.println("<a href='logout.do?username=" + username + "'>ע�� " + username + "</a>");
		out.println("</div>");
		out.println("<form method='post' action='message.do'>");
		out.println("����������...<br>");

		String blabla = request.getParameter("blabla");
		if (blabla == null) {
			blabla = "";
		} else {
			out.println("��ϢҪ�� 140 ������<br>");
		}
		out.println("<textarea cols='60' row='4' name='blabla'>" + blabla + "</textarea>");
		out.println("<br>");
		out.println("<button type='submit'>�ͳ�</button>");
		out.println("</form>");
		out.println(
				"<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
		out.println("<thead>");
		out.println("<tr><th><hr></th></tr>");
		out.println("</thead>");
		out.println("<tbody>");
		// ��ȡ�ļ��������ʾ��Ϣ
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		Map<Date, String> messages = userService.readMessage(username);
		DateFormat dateformat = DateFormat.getDateTimeInstance(DateFormat.FULL, 
				DateFormat.FULL, Locale.CHINESE);
		for (Date date : messages.keySet()) {
			out.println("<tr><td style='vertical-align: top;'>");
			out.println(username + "<br>");
			out.println(messages.get(date) + "<br>");
			out.println(dateformat.format(date));
			out.println("<a href='delete.do?message=" + date.getTime() + "'>ɾ��</a>");
			out.println("<hr><td><tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("<hr style='width: 100%; height: 1px;'>");

		out.println("</body>");
		out.println("</html>");
		out.close();
	}

//	// ����.txt�ļ���
//	private class TxtFilenameFilter implements FilenameFilter {
//		@Override
//		public boolean accept(File dir, String name) {
//			return name.endsWith(".txt");
//		}
//	}
//
//	private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();
//
//	// TreeMap�����ã���Ϊϣ����Ϣ������Խ����Խ����ͷ��ʾ
//	private class DateComparator implements Comparator<Date> {
//		@Override
//		public int compare(Date d1, Date d2) {
//			return -d1.compareTo(d2);
//		}
//	}
//
//	private DateComparator comparator = new DateComparator();
//
//	private Map<Date, String> readMessage(String username) throws IOException {
//		File border = new File(USERS + "/" + username);
//		String[] txts = border.list(filenameFilter);
//
//		Map<Date, String> messages = new TreeMap<Date, String>(comparator);
//		for (String txt : txts) {
//			BufferedReader reader = new BufferedReader(
//					new InputStreamReader(new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
//			String text = null;
//			StringBuilder builder = new StringBuilder();
//			while ((text = reader.readLine()) != null) {
//				builder.append(text);
//			}
//			Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
//			messages.put(date, builder.toString());
//			reader.close();
//		}
//
//		return messages;
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
