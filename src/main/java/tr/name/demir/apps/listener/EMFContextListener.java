package tr.name.demir.apps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import tr.name.demir.apps.dao.EntityManagerHelper;

@WebListener
public class EMFContextListener implements ServletContextListener {
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		EntityManagerHelper.closeEntityManagerFactory();
	}

}
