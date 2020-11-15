package bringg.home.assignment.filters;

import bringg.home.assignment.dto.PointData;
import bringg.home.assignment.dto.Task;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IdenticalPointsFilterTest {

    private final IdenticalPointsFilter identicalPointsFilter = new IdenticalPointsFilter();

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
        List<Task> result = identicalPointsFilter.filter(tasks, distanceMatrix);

        assertNotNull(result);
        assertEquals(result.size(), 6);
        assertEquals(result.get(0), task2);
        assertEquals(result.get(1), task3);
        assertEquals(result.get(2), task4);
        assertEquals(result.get(3), task6);
        assertEquals(result.get(4), task7);
        assertEquals(result.get(5), task8);

        String resultA = distanceMatrix[0][0];
        String resultB = distanceMatrix[1][1];
        String resultC = distanceMatrix[2][2];

        assertEquals(resultA, "0.00");
        assertEquals(resultB, "0.00");
        assertEquals(resultC, "0.00");
    }
}