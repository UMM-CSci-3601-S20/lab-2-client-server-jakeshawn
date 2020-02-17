package umm3601.todo;

//import umm3601.todo.Todo.TodoOwnerComparator;
import umm3601.todo.Todo.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TodoDatabase {

  private  Todo[] allTodos;

  public TodoDatabase( String todoDataFile) throws IOException {

     Gson gson = new Gson();
     InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  public Todo getTodo( String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos( Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;
    // Filter by owners if defined
    if (queryParams.containsKey("owner")) {
       String targetOwner = queryParams.get("owner").get(0);
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }
    // Filter by category if defined
    if (queryParams.containsKey("category")) {
       String targetCategory = queryParams.get("category").get(0);
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

    // Filter by status if defined
    if (queryParams.containsKey("status")) {
       String targetStatus = queryParams.get("status").get(0);
      boolean specifiedStatus;

      if (targetStatus.equals("complete")) {
        specifiedStatus = true;
      } else {
        specifiedStatus = false;
      }
      filteredTodos = filterTodosByStatus(allTodos, specifiedStatus);
    }

    // Filter by body if defined
    if (queryParams.containsKey("contains")) {
       String targetBody = queryParams.get("contains").get(0);
      filteredTodos = filterTodosByBody(filteredTodos, targetBody);
    }
    if(queryParams.containsKey("limit")){
      int targetLimit = Integer.parseInt(queryParams.get("limit").get(0));
      filteredTodos = limitTodos(targetLimit);
    }

    return filteredTodos;
  }

  public Todo[] filterTodosByOwner( Todo[] todos,  String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByCategory( Todo[] todos,  String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatus( Todo[] todos,  boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status.equals(targetStatus)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody( Todo[] todos,  String targetBody) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }

  public Todo[] limitTodos( int limit) {
     Todo[] limitedTodos = new Todo[limit];
    for (int i = 0; i < limit; i++) {
      limitedTodos[i] = allTodos[i];
    }
    return limitedTodos;
  }

  public Todo[] sortByOwner() throws ClassCastException{
    Todo[] result = allTodos.clone();

    //Arrays.sort(result, new TodoOwnerComparator());

    return result;
  }

}
