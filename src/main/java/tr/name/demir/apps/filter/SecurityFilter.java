package tr.name.demir.apps.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.name.demir.apps.controller.LoginBean;

@WebFilter(urlPatterns = "/private/*", filterName = "securityFilter")

public class SecurityFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!isLoggedIn(request)) {
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getContextPath() + "/login.xhtml";
			res.sendRedirect(url);
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	private boolean isLoggedIn(ServletRequest request) {
		LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");
		return loginBean != null && loginBean.getUser() != null;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
}