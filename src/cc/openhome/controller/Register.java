package cc.openhome.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.UserService;

@WebServlet(
		urlPatterns= {"/register.do"},
		initParams= {
				@WebInitParam(name = "SUCCESS_VIEW", value = "success.view"),
				@WebInitParam(name = "ERROR_VIEW", value = "error.view")
		}
		)
public class Register extends HttpServlet{
//	private final String USERS = "F:/eclipse/workspace/Gossip/users";
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	
	@Override
	public void init() throws ServletException {
		SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
		ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//��ȡ�������
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmedPasswd = request.getParameter("confirmedPasswd");
		
		UserService userService = (UserService) getServletContext().getAttribute("userServive");
		
		//��֤�������
		List<String> errors = new ArrayList<String>();
		if (userService.isInvalidEmail(email)) {
			errors.add("δ��д�ʼ����ʼ���ʽ����ȷ");
		}
		if (userService.isInvalidUsername(username)) {
			errors.add("�û�����Ϊ�ջ��Ѵ���");
		}
		if (userService.isInvalidPassword(password, confirmedPasswd)) {
			errors.add("��ȷ��������ϸ�ʽ���ٴ�ȷ������");
		}
		String resultPage = ERROR_VIEW;
		//������֤���������ռ������ListΪ��������
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}
		else {
			resultPage = SUCCESS_VIEW;
			//�����û�����
			userService.createUserData(email, username, password);
		}
		
		request.getRequestDispatcher(resultPage).forward(request, response);
	}

//	//�����û����ϳأ���profile�д����ʼ�������
//	private void createUserData(String email, String username, String password)  
//			throws IOException{
//		// TODO Auto-generated method stub
//		File userhome = new File(USERS + "/" + username);
//		userhome.mkdirs();
//		BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
//		writer.write(email + "\t" + password);
//		writer.close();
//	}

//	private boolean isInvalidPassword(String password, String confirmedpasswd) {
//		// TODO Auto-generated method stub
//		return password == null ||
//				password.length() < 6 ||
//				password.length() > 16 ||
//				!password.equals(confirmedpasswd);
//	}

//	//����û����ϼ��Ƿ񴴽���ȷ���û��Ƿ�ע��
//	private boolean isInvalidUsername(String username) {
//		// TODO Auto-generated method stub
//		for(String file : new File(USERS).list()) {
//			if (file.equals(username)) {
//				return true;
//			}
//		}
//		return false;
//	}

//	private boolean isInvalidEmail(String email) {
//		// TODO Auto-generated method stub
//		return email == null ||
//				!email.matches("^[_a-z0-9-]+([.]" + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
//	}
}
