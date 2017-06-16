package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.TodoDAO;
import tr.name.demir.apps.entity.Todo;
import tr.name.demir.apps.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TodoEditBean implements Serializable {
	private Todo todo;
	private TodoDAO todoDAO = new TodoDAO();

	public TodoEditBean() {
		int id = Integer.parseInt(FacesUtil.getParamter("id"));
		todo = todoDAO.findById(id);
	}

	public String save() {
		todoDAO.update(todo);
		return "/private/todo/list?faces-redirect=true";
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

}
