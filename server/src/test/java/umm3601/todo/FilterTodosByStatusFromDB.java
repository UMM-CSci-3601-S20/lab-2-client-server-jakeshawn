package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.Database filterUsersByAge and listUsers with _age_ query
 * parameters
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
}
