package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


/**
 * Tests umm3601.todo.Database
 * Tests if there is the correct number of todos displayed if that status would be entered into the website
 */
public class FilterTodosByStatusFromDB {

  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] statusTrue = db.filterTodosByStatus(allTodos, true);
    assertEquals(143, statusTrue.length, "Incorrect number of Todos with status true");

    Todo[] statusFalse = db.filterTodosByStatus(allTodos, false);
    assertEquals(157, statusFalse.length, "Incorrect number of Todos with status false");
  }
  /*
  @Test
  public void listTodosWithStatusFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("status", Arrays.asList(new boolean[] { true }));
    Todo[] statusTrue = db.listTodos(queryParams);
    assertEquals(143, statusTrue.length, "Incorrect number of Todos with status true");

    queryParams.get("status", Arrays.asList(new boolean[] { false }));
    Todo[] statusFalse = db.listTodos(queryParams);
    assertEquals(157, statusFalse.length, "Incorrect number of Todos with status false");
  }
  */
}
