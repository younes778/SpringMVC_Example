package spring.app.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import spring.app.model.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int count = 3;
	
	static {
		todos.add(new Todo(1, "y", "Learn Spring MVC", new Date(),
				false));
		todos.add(new Todo(2, "y", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "y", "Learn Hibernate", new Date(),
				false));
	}
	
	public List<Todo> getTodosByUser(String user){
		List<Todo> userTodos = new ArrayList<Todo>();
		for (Todo todo:todos) {
			if (todo.getUser().equals(user))
				userTodos.add(todo);
		}
		return userTodos;
	}
	
	public void addTodo(String user, String desc, Date targetDate) {
		todos.add(new Todo(++count, user, desc, targetDate, false));
	}
	
	public void deleteTodo(int id) {
		for (Todo todo:todos) {
			if (todo.getId()==id) {
				count--;
				todos.remove(todo);
				return;
			}
		}
	}
}
