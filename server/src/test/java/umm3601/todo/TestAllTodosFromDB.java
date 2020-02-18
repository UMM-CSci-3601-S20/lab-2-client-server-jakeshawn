package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.Test;


/**
 * Tests umm3601.todo.Database
 * Tests if all of the todos are displayed by checking that there is 143 true statuses todos and 157 false statuses adding up to a total of 300 todos.
 * Note that this is the same test as FilterTodosByStatusFromDB.java
 */
public class TestAllTodosFromDB {

  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] statusTrue = db.filterTodosByStatus(allTodos, true);
    assertEquals(143, statusTrue.length, "Incorrect number of Todos with status true");

    Todo[] statusFalse = db.filterTodosByStatus(allTodos, false);
    assertEquals(157, statusFalse.length, "Incorrect number of Todos with status false");
  }

}
