package cc.openhome.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter (
	urlPatterns = {"/delete.do",
			"/logout.do",
			"/message.do",
			"/member.view"
	},
	initParams = {
			@WebInitParam(name = "LOGIN_VIEW", value = "index.html")
	}
)


public class MemberFilter implements Filter {
	private String LOGIN_VIEW;
	public void init(FilterConfig config) 
			throws ServletException{
		this.LOGIN_VIEW = config.getInitParameter(LOGIN_VIEW); //设置登陆页面
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute("login") != null) {
			chain.doFilter(request, response); //只有在具备login属性时，才调用doFilter()
		}
		else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(LOGIN_VIEW); //否则重定向至登录页面
		}
	}
	
}
