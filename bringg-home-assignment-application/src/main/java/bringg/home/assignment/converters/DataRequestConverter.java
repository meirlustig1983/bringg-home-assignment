package bringg.home.assignment.converters;

import bringg.home.assignment.dto.Point;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataRequestConverter {

    public int convertThreads(String threads) {
        int threadPoolSize = 1;

        if (!StringUtils.isEmpty(threads)) {
            try {
                threadPoolSize = Integer.parseInt(threads);
            } catch (Exception e) {
                String msg = String.format("missing threads pool size. originalThreadsData: %s. try to pass data like that: 2. use 'help' method for more info.", threads);
                throw new IllegalArgumentException(msg);
            }
        }
        return threadPoolSize;
    }

    public List<Point> convertPoints(String points) {

        if (StringUtils.isEmpty(points)) {
            String msg = String.format("missing data points. originalPointsData: %s. try to pass data like that (1,2)(-1,4): 1,2,-1,4. use 'help' method for more info.", points);
            throw new IllegalArgumentException(msg);
        }

        String[] numbers = points.split(",");
        List<Point> returnArr = new ArrayList<>();

        if (numbers.length % 2 == 1) {
            String msg = String.format("corrupted data points. originalPointsData: %s. try to pass data like that (1,2)(-1,4): 1,2,-1,4. use 'help' method for more info.", points);
            throw new IllegalArgumentException(msg);
        } else {
            try {
                for (int i = 0; i < numbers.length; ++i) {
                    Point point = new Point().setX(Integer.parseInt(numbers[i++])).setY(Integer.parseInt(numbers[i]));
                    returnArr.add(point);
                }
            } catch (Exception e) {
                String msg = String.format("corrupted data points. originalPointsData: %s. try to pass data like that (1,2)(-1,4): 1,2,-1,4. use 'help' method for more info.", points);
                throw new IllegalArgumentException(msg);
            }
        }
        return returnArr;
    }
}
