package bringg.home.assignment.services;

import bringg.home.assignment.converters.PointConverter;
import bringg.home.assignment.converters.PointDataConverter;
import bringg.home.assignment.dto.Point;
import bringg.home.assignment.filters.DoubleTasksFilter;
import bringg.home.assignment.filters.IdenticalPointsFilter;
import bringg.home.assignment.filters.TasksFilter;
import bringg.home.assignment.utils.MathUtils;
import bringg.home.assignment.utils.NodesUtils;
import bringg.home.assignment.utils.QueuesUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DistanceMatrixServiceTest {

    private final MathUtils mathUtils = new MathUtils();

    private final PointConverter pointConverter = new PointConverter();

    private final PointDataConverter pointDataConverter = new PointDataConverter();

    private final List<TasksFilter> tasksFilters = Arrays.asList(new DoubleTasksFilter(), new IdenticalPointsFilter());

    private final QueuesUtils queuesUtil = new QueuesUtils();

    private final NodesUtils nodesUtils = new NodesUtils(mathUtils);

    private final DistanceMatrixService service = new DistanceMatrixService(pointConverter, pointDataConverter,
        tasksFilters, queuesUtil, nodesUtils);

    @Test
    public void calculateDistanceMatrix() {

        Point pointA = new Point().setX(2).setY(-1);
        Point pointB = new Point().setX(-2).setY(2);
        Point pointC = new Point().setX(-1).setY(1);

        List<Point> points = Arrays.asList(pointA, pointB, pointC);

        String[][] result = service.calculateDistanceMatrix(points, 2);

        assertNotNull(result);

        assertEquals(result[0][0], "0.00");
        assertEquals(result[0][1], "5.00");
        assertEquals(result[0][2], "3.61");
        assertEquals(result[1][0], "5.00");
        assertEquals(result[1][1], "0.00");
        assertEquals(result[1][2], "1.41");
        assertEquals(result[2][0], "3.61");
        assertEquals(result[2][1], "1.41");
        assertEquals(result[2][2], "0.00");
    }

    @Test
    public void calculateDistanceMatrix_WithoutThreadPoolSize() {

        Point pointA = new Point().setX(2).setY(-1);
        Point pointB = new Point().setX(-2).setY(2);
        Point pointC = new Point().setX(-1).setY(1);

        List<Point> points = Arrays.asList(pointA, pointB, pointC);
        assertThrows(IllegalArgumentException.class, () -> service.calculateDistanceMatrix(points, 0));
    }

    @Test
    public void calculateDistanceMatrix_WithoutPoints() {
        assertThrows(IllegalArgumentException.class, () -> service.calculateDistanceMatrix(Collections.EMPTY_LIST, 1));
    }

    @Test
    public void calculateDistanceMatrix_WithoutPoints2() {
        assertThrows(IllegalArgumentException.class, () -> service.calculateDistanceMatrix(null, 1));
    }
}