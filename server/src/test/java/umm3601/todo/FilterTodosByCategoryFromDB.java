package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests if there is the correct number of todos displayed if that category would be entered into the website
 */
public class FilterTodosByCategoryFromDB {

  @Test
  public void filterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] categoryVideoGamesTodos = db.filterTodosByCategory(allTodos, "video games");
    assertEquals(71, categoryVideoGamesTodos.length, "Incorrect number of Todos with category video games");

    Todo[] categoryHomeworkTodos = db.filterTodosByCategory(allTodos, "homework");
    assertEquals(79, categoryHomeworkTodos.length, "Incorrect number of Todos with category homework");
  }

  @Test
  public void listTodosWithCategoryFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("category", Arrays.asList(new String[] { "software design" }));
    Todo[] categorySoftwareDesignTodos = db.listTodos(queryParams);
    assertEquals(74, categorySoftwareDesignTodos.length, "Incorrect number of Todos with category software design");

    queryParams.put("category", Arrays.asList(new String[] { "groceries" }));
    Todo[] categoryGroceriesTodos = db.listTodos(queryParams);
    assertEquals(76, categoryGroceriesTodos.length, "Incorrect number of Todos with category groceries");
  }
}
