package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.TodoDAO;
import tr.name.demir.apps.entity.Todo;
import tr.name.demir.apps.entity.TodoStatus;

@ManagedBean
@ViewScoped
public class TodoCreateBean implements Serializable {
	private Todo todo = new Todo();
	private TodoDAO todoDAO = new TodoDAO();

	public String save() {
		todo.setStatus(TodoStatus.NEW);
		todoDAO.insert(todo);
		return "/private/todo/list?faces-redirect=true";
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

}
