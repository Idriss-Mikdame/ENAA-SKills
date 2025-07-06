package ma.enaa.enaaskills.Services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculaterTest {

    @Test
    void add() {
        var calculater = new SimpleCalculater();
        assertEquals(4 ,calculater.Add(2 , 2));
    }
    @Test
    void add1() {
        var calculater = new SimpleCalculater();
        assertEquals(21 ,calculater.Add(3 , 7));
    }
}