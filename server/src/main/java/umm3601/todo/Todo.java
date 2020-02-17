package umm3601.todo;

public class Todo{

  public String _id;
  public String owner;
  public Boolean status;
  public String body;
  public String category;

  public String getId() {
    return _id;
  }
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

public class TodoOwnerComparator implements Comparable<Todo>{

   @Override
   public int compareTo(Todo other) {
     return _id.compareTo(other._id);
   }
}
public class TodoBodyComparator implements Comparable<Todo>{

  @Override
  public int compareTo(Todo other) {
    return body.compareTo(other.body);
  }
}
public class TodoStatusComparator implements Comparable<Todo>{

  @Override
  public int compareTo(Todo other) {
    return status.compareTo(other.status);
  }
}
public class TodoCategoryComparator implements Comparable<Todo>{

  @Override
  public int compareTo(Todo other) {
    return category.compareTo(other.category);
  }
}

}
