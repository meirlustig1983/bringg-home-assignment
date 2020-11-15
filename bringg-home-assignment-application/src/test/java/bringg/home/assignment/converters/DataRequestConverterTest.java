package bringg.home.assignment.converters;

import bringg.home.assignment.dto.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataRequestConverterTest {

    private final DataRequestConverter dataRequestConverter = new DataRequestConverter();

    @Test
    public void convertThreads() {
        int result = dataRequestConverter.convertThreads("5");
        assertEquals(result, 5);
    }

    @Test
    public void convertThreads_WithFloatNumber() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertThreads("5.4"));
    }

    @Test
    public void convertThreads_WithoutNumber() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertThreads("5gfh4"));
    }

    @Test
    public void convertThreads_WithEmptyString() {
        int result = dataRequestConverter.convertThreads("");
        assertEquals(result, 1);
    }

    @Test
    public void convertThreads_WithNull() {
        int result = dataRequestConverter.convertThreads(null);
        assertEquals(result, 1);
    }

    @Test
    public void convertPoints() {
        List<Point> result = dataRequestConverter.convertPoints("1,2,3,4");
        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), new Point().setX(1).setY(2));
        assertEquals(result.get(1), new Point().setX(3).setY(4));
    }

    @Test
    public void convertPoints_withNull() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertPoints(null));
    }

    @Test
    public void convertPoints_withEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertPoints(""));
    }

    @Test
    public void convertPoints_withOddData() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertPoints("1,1,1"));
    }

    @Test
    public void convertPoints_withNotNumbersData() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertPoints("1,f1,1,3"));
    }

    @Test
    public void convertPoints_withNotNumbersData2() {
        assertThrows(IllegalArgumentException.class, () -> dataRequestConverter.convertPoints("1dgfdg"));
    }
}
