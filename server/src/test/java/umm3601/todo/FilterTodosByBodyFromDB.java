package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


/**
 * Tests umm3601.todo.Database
 * Tests if there is the correct number of todos displayed if that body would be entered into the website
 */
public class FilterTodosByBodyFromDB {

  @Test
  public void filterTodosByBody() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] bodyIpsumTodos = db.filterTodosByBody(allTodos, "Ipsum");
    assertEquals(12, bodyIpsumTodos.length, "Incorrect number of Todos with body Ipsum");

    Todo[] bodyCulpaTodos = db.filterTodosByBody(allTodos, "culpa");
    assertEquals(76, bodyCulpaTodos.length, "Incorrect number of Todos with body  culpa");
  }

}
