package goon.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Deadline actual = new Deadline("sample deadline", LocalDate.of(1111,11,11));
        Deadline expected = new Deadline("sample deadline", LocalDate.parse("1111-11-11"));
        assertEquals(actual.toFileFormat(), expected.toFileFormat());
    }
}