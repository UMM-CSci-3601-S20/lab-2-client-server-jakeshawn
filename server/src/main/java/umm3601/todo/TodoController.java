package umm3601.todo;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class TodoController{

  private TodoDatabase database;

   /**
   * Construct a controller for todos.
   * <p>
   * This loads the "database" of todo info from a JSON file and stores that
   * internally so that (subsets of) users can be returned in response to
   * requests.
   *
   * @param database the `Database` containing todo data
   */
  public TodoController(TodoDatabase database) {
    this.database = database;
  }

  public void getTodo(Context ctx) {

    String id = ctx.pathParam("id", String.class).get();
    Todo todo = database.getTodo(id);
    if (todo != null) {
      ctx.json(todo);
      ctx.status(201);
    } else {
      throw new NotFoundResponse("No todo with id " + id + " was found.");
    }
  }
  public void getTodos(Context ctx) {
    Todo[] todos = database.listTodos(ctx.queryParamMap());
    ctx.json(todos);
  }


}
