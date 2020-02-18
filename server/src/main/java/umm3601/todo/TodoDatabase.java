package umm3601.todo;

import umm3601.todo.Todo.TodoOwnerComparator;
import umm3601.todo.Todo.TodoCategoryComparator;
import umm3601.todo.Todo.TodoBodyComparator;
import umm3601.todo.Todo.TodoStatusComparator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {

    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  public Todo getTodo(String id){
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;
    if (queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner").get(0);
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }
      // Filter category if defined
    if (queryParams.containsKey("category")) {
        String targetCategory = queryParams.get("category").get(0);
        filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }

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

    if (queryParams.containsKey("contains")) {
      String targetBody = queryParams.get("contains").get(0);
      filteredTodos = filterTodosByBody(filteredTodos, targetBody);
    }
    if(queryParams.containsKey("limit")){
      int targetLimit = Integer.parseInt(queryParams.get("limit").get(0));
      filteredTodos = limitTodos(targetLimit);
    }
    if(queryParams.containsKey("orderBy")){
      String targetOrder = queryParams.get("orderBy").get(0);

      if(targetOrder.equals("owner")){
        filteredTodos = sortByOwner(allTodos);
      }
      if(targetOrder.equals("status")){
        filteredTodos = sortByStatus(allTodos);
      }
      if(targetOrder.equals("category")){
        filteredTodos = sortByCategory(allTodos);
      }
      if(targetOrder.equals("body")){
        filteredTodos = sortByBody(allTodos);
      }
    }

    return filteredTodos;
  }

  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByCategory(Todo[] todos, String targetCategory) {
    return Arrays.stream(todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status.equals(targetStatus)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetBody) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }

  public Todo[] limitTodos(int limit){
    Todo[] limitedTodos = new Todo[limit];
    for(int i=0; i<limit;i++){
      limitedTodos[i] = allTodos[i];
    }
    return limitedTodos;
  }

  public Todo[] sortByOwner(Todo[] todos){
    Todo[] result = todos;
    TodoOwnerComparator comparator = new TodoOwnerComparator();

    Arrays.sort(result,comparator);

    return result;
  }

  public Todo[] sortByBody(Todo[] todos){
    Todo[] result = todos;
    TodoBodyComparator comparator = new TodoBodyComparator();

    Arrays.sort(result,comparator);

    return result;
  }

  public Todo[] sortByCategory(Todo[] todos){
    Todo[] result = todos;
    TodoCategoryComparator comparator = new TodoCategoryComparator();

    Arrays.sort(result,comparator);

    return result;
  }

  public Todo[] sortByStatus(Todo[] todos){
    Todo[] result = todos;
    TodoStatusComparator comparator = new TodoStatusComparator();

    Arrays.sort(result,comparator);

    return result;
  }

}
