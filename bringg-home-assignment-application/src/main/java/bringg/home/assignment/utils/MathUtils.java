package bringg.home.assignment.utils;

import bringg.home.assignment.dto.PointData;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
public class MathUtils {

    public double calcDistance(PointData pointA, PointData pointB) {
        log.info("calculate distance between two 2D points. pointA: ({},{}), pointB: ({},{})", pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
        return roundDouble(Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2)), 2);
    }

    @VisibleForTesting
    protected double roundDouble(double number, int places) {
        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}