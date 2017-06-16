package tr.name.demir.apps.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import tr.name.demir.apps.dao.TodoDAO;
import tr.name.demir.apps.entity.Todo;

@ManagedBean
@RequestScoped
public class TodoListBean implements Serializable {
	private List<Todo> todoList;
	private TodoDAO todoDAO = new TodoDAO();

	public TodoListBean() {
		todoList = todoDAO.getList();
	}

	public void statusChanged(AjaxBehaviorEvent event) {
		Todo todo = (Todo) event.getComponent().getAttributes().get("todo");
		todoDAO.update(todo);
	}

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}

}
