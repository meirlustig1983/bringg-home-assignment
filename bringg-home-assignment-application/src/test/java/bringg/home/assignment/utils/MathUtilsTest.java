package bringg.home.assignment.utils;

import bringg.home.assignment.dto.PointData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    private final MathUtils mathUtils = new MathUtils();

    @Test
    public void calcDistance1() {

        PointData pointA = new PointData().setX(2).setY(-1);
        PointData pointB = new PointData().setX(-2).setY(2);

        double result = mathUtils.calcDistance(pointA, pointB);

        assertEquals(result, 5);
    }

    @Test
    public void calcDistance2() {

        PointData pointA = new PointData().setX(2).setY(-1);
        PointData pointB = new PointData().setX(-2).setY(2);

        double result = mathUtils.calcDistance(pointB, pointA);

        assertEquals(result, 5);
    }

    @Test
    public void roundDouble() {
        double result = mathUtils.roundDouble(1.23243545, 2);

        assertEquals(result, 1.23);
    }
}