package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.Database filterUsersByAge and listUsers with _age_ query
 * parameters
 */
public class FilterTodosByOwnerFromDB {

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ownerBarryTodos = db.filterTodosByOwner(allTodos, "Barry");
    assertEquals(51, ownerBarryTodos.length, "Incorrect number of Todos with owner name Barry");

    Todo[] ownerFryTodos = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals(61, ownerFryTodos.length, "Incorrect number of Todos with owner name Fry");
  }

  @Test
  public void listTodosWithOwnerFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] { "Barry" }));
    Todo[] ownerBarryTodos = db.listTodos(queryParams);
    assertEquals(51, ownerBarryTodos.length, "Incorrect number of Todos with owner name Barry");

    queryParams.put("owner", Arrays.asList(new String[] { "Fry" }));
    Todo[] ownerFryTodos = db.listTodos(queryParams);
    assertEquals(61, ownerFryTodos.length, "Incorrect number of Todos with owner name Fry");
  }
}
