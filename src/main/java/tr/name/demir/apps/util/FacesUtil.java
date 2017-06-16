package tr.name.demir.apps.util;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ProjectStage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtil {

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static Application getApplication() {
		return getFacesContext().getApplication();
	}

	public static ProjectStage getProjectStage() {
		return getApplication().getProjectStage();
	}

	public static HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	public static String getParamter(String name) {
		return getRequest().getParameter(name);
	}

	private static void addMessage(String message, Severity severity) {
		getFacesContext().addMessage(null, new FacesMessage(severity, message, null));
	}

	public static void addFatal(String message) {
		addMessage(message, FacesMessage.SEVERITY_FATAL);
	}

	public static void addError(String message) {
		addMessage(message, FacesMessage.SEVERITY_ERROR);
	}

	public static void addWarn(String message) {
		addMessage(message, FacesMessage.SEVERITY_WARN);
	}

	public static void addInfo(String message) {
		addMessage(message, FacesMessage.SEVERITY_INFO);
	}

}
