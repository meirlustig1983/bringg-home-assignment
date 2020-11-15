package bringg.home.assignment.filters;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DoubleTasksFilterTest {

    private final DoubleTasksFilter doubleTasksFilter = new DoubleTasksFilter();

    @Test
    public void filter() {

        String[][] distanceMatrix = new String[3][3];

        PointData pointDataA = new PointData().setId(0).setX(2).setY(-1);
        PointData pointDataB = new PointData().setId(1).setX(-2).setY(2);
        PointData pointDataC = new PointData().setId(2).setX(-1).setY(1);

        Task task1 = new Task().setPointA(pointDataA).setPointB(pointDataA);
        Task task2 = new Task().setPointA(pointDataA).setPointB(pointDataB);
        Task task3 = new Task().setPointA(pointDataA).setPointB(pointDataC);
        Task task4 = new Task().setPointA(pointDataB).setPointB(pointDataA);
        Task task5 = new Task().setPointA(pointDataB).setPointB(pointDataB);
        Task task6 = new Task().setPointA(pointDataB).setPointB(pointDataC);
        Task task7 = new Task().setPointA(pointDataC).setPointB(pointDataA);
        Task task8 = new Task().setPointA(pointDataC).setPointB(pointDataB);
        Task task9 = new Task().setPointA(pointDataC).setPointB(pointDataC);

        List<Task> tasks = Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8, task9);
        List<Task> result = doubleTasksFilter.filter(tasks, distanceMatrix);

        assertNotNull(result);
        assertEquals(result.size(), 6);
        assertEquals(result.get(0), task1);
        assertEquals(result.get(1), task2);
        assertEquals(result.get(2), task3);
        assertEquals(result.get(3), task5);
        assertEquals(result.get(4), task6);
        assertEquals(result.get(5), task9);

        String result1 = distanceMatrix[0][0];
        String result2 = distanceMatrix[0][1];
        String result3 = distanceMatrix[0][2];
        String result4 = distanceMatrix[1][0];
        String result5 = distanceMatrix[1][1];
        String result6 = distanceMatrix[1][2];
        String result7 = distanceMatrix[2][0];
        String result8 = distanceMatrix[2][1];
        String result9 = distanceMatrix[2][2];

        assertEquals(result1, "-1.00");
        assertEquals(result2, "-1.00");
        assertEquals(result3, "-1.00");
        assertEquals(result4, "-1.00");
        assertEquals(result5, "-1.00");
        assertEquals(result6, "-1.00");
        assertEquals(result7, "-1.00");
        assertEquals(result8, "-1.00");
        assertEquals(result9, "-1.00");
    }
}
