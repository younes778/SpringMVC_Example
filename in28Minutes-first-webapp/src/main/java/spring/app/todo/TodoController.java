package spring.app.todo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodoListRequest(ModelMap model) {
		String user = (String) model.get("name");
		model.addAttribute("name",user);
		model.put("todos", service.getTodosByUser(user));
		return "list-todos";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage() {
		return "todo";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @RequestParam String desc) {
		service.addTodo((String) model.get("name"), desc, new Date());
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
	}
	
}
