package cc.openhome.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

//import org.apache.commons.lang3.StringEscapeUtils;

public class EscapeWrapper extends HttpServletRequestWrapper {
	public EscapeWrapper(HttpServletRequest request) {
		super(request); //���ù��캯��������HttpServletRequestʵ��
	}
	
	@Override
	public String getParameter(String name) { //���¶���getParameter()����
		String value = getRequest().getParameter(name);
		//��ȡ�õ��������ֵ�����ַ��滻
		return StringEscapeUtils.escapeHtml(value);
	}

}
