package cc.openhome.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

//import org.apache.commons.lang3.StringEscapeUtils;

public class EscapeWrapper extends HttpServletRequestWrapper {
	public EscapeWrapper(HttpServletRequest request) {
		super(request); //调用构造函数，传入HttpServletRequest实例
	}
	
	@Override
	public String getParameter(String name) { //重新定义getParameter()方法
		String value = getRequest().getParameter(name);
		//将取得的请求参数值进行字符替换
		return StringEscapeUtils.escapeHtml(value);
	}

}
