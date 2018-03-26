package cc.openhome.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class EscapeFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {}
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//将原请求对象包裹至EscapeWrapper中
	    HttpServletRequest requestWrapper = new EscapeWrapper((HttpServletRequest) request);
	    //将EscapeWrapper对象当做请求对象传入doFilter()
	    chain.doFilter(requestWrapper, response);
	}

    public void destroy() {}
}
