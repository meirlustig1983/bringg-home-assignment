package bringg.home.assignment.converters;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PointDataConverterTest {

    private final PointDataConverter converter = new PointDataConverter();

    @Test
    public void createTasks() {

        PointData pointDataA = new PointData().setId(0).setX(2).setY(-1);
        PointData pointDataB = new PointData().setId(1).setX(-2).setY(2);

        Task taskA = new Task().setPointA(pointDataA).setPointB(pointDataA);
        Task taskB = new Task().setPointA(pointDataA).setPointB(pointDataB);
        Task taskC = new Task().setPointA(pointDataB).setPointB(pointDataA);
        Task taskD = new Task().setPointA(pointDataB).setPointB(pointDataB);

        List<Task> result = converter.covertToTasksList(Arrays.asList(pointDataA, pointDataB));

        assertNotNull(result);
        assertEquals(result.size(), 4);
        assertEquals(result.get(0), taskA);
        assertEquals(result.get(1), taskB);
        assertEquals(result.get(2), taskC);
        assertEquals(result.get(3), taskD);
    }
}