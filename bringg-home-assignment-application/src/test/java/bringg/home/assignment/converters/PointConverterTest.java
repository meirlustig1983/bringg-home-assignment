package bringg.home.assignment.converters;

import bringg.home.assignment.dto.Point;
import bringg.home.assignment.dto.PointData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PointConverterTest {

    private final PointConverter converter = new PointConverter();

    @Test
    public void calcDistance() {

        Point pointA = new Point().setX(2).setY(-1);
        PointData pointDataA = new PointData().setId(0).setX(2).setY(-1);
        Point pointB = new Point().setX(-2).setY(2);
        PointData pointDataB = new PointData().setId(1).setX(-2).setY(2);

        List<PointData> result = converter.convertToPointDataList(Arrays.asList(pointA, pointB));

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0), pointDataA);
        assertEquals(result.get(1), pointDataB);
    }
}