package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import umm3601.Server;

public class LimitTodos{
    /**
 * Tests umm3601.todo.Database 
 */
    @Test
    public static void LimitTodosInsideOfRange() throws IOException {

        TodoDatabase db = new TodoDatabase("/todos.json");        
        Todo[] testArray = db.limitTodos(5);

        assertEquals(5, testArray.length);
    }

    @Test
    public static void LimitTodosOutsideOfRange()throws IOException {

        TodoDatabase db = new TodoDatabase("/todos.json");        
        Todo[] testArray = db.limitTodos(400);

        assertEquals(300, testArray.length);

    }

}