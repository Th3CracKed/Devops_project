package travis_example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleTest {
    private Example example = new Example();
    @Test
    public void doStuff() {
        assertTrue(example.doStuff());
    }
}