package goon.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest(){
        ToDo todo = new ToDo("compete the ip tasks");
        String actual = todo.toString();
        String expected = "[T][ ] compete the ip tasks";
        assertEquals(expected, actual);
    }
}
