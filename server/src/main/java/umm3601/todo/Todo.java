package umm3601.todo;

import java.util.Comparator;

public class Todo{

  public String _id;
  public String owner;
  public Boolean status;
  public String body;
  public String category;

  public String getOwner(){
    return owner;
  }
  public boolean getStatus(){
    return status;
  }
  public String getBody(){
    return body;
  }
  public String getCategory(){
    return category;
  }

public static class TodoOwnerComparator implements Comparator<Todo>{

   public int compare(Todo todo1, Todo todo2) {
     return todo1.getOwner().compareTo(todo2.getOwner());
   }
}
public static class TodoBodyComparator implements Comparator<Todo>{

  public int compare(Todo todo1, Todo todo2) {
    return todo1.getBody().compareTo(todo2.getBody());
  }
}
public static class TodoStatusComparator implements Comparator<Todo>{

  public int compare(Todo todo1, Todo todo2) {
    return Boolean.compare(todo1.getStatus(), todo2.getStatus());
  }
}
public static class TodoCategoryComparator implements Comparator<Todo>{

  public int compare(Todo todo1, Todo todo2) {
    return todo1.getCategory().compareTo(todo2.getCategory());
  }
}

}
