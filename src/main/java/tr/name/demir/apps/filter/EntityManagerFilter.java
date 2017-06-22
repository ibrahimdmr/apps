package tr.name.demir.apps.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import tr.name.demir.apps.dao.EntityManagerHelper;

@WebFilter("/*")
public class EntityManagerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			EntityManagerHelper.beginTransaction();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
			EntityManagerHelper.commit();
		} catch (RuntimeException e) {
			if (EntityManagerHelper.getEntityManager() != null && EntityManagerHelper.getEntityManager().isOpen())
				EntityManagerHelper.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
