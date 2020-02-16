package umm3601.todo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {

    Gson gson = new Gson();
    FileReader reader = new FileReader(todoDataFile);
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

    // // Filter age if defined
    // if (queryParams.containsKey("age")) {
    // int targetAge = Integer.parseInt(queryParams.get("age").get(0));
    // filteredUsers = filterUsersByAge(filteredUsers, targetAge);
    // }
    // // Filter company if defined
    // if (queryParams.containsKey("company")) {
    // String targetCompany = queryParams.get("company").get(0);
    // filteredUsers = filterUsersByCompany(filteredUsers, targetCompany);
    // }
    // Process other query parameters here...

    // public User[] filterUsersByCompany(User[] users, String targetCompany) {
    // return Arrays.stream(users).filter(x ->
    // x.company.equals(targetCompany)).toArray(User[]::new);
    // }

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




}
